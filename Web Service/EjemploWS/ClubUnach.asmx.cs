using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace EjemploWS
{
    /// <summary>
    /// Descripción breve de Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio Web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class ClubUnach : System.Web.Services.WebService
    {
        private BDUtil util = new BDUtil("localhost", "clubunach", "root", "");
       
        [WebMethod]
        public object[][] IniciarSesion(string Usuario, string contraseña)
        {
            string query = "SELECT * FROM `cliente` where " +
                $"(telefono='{Usuario}' or correo_electronico='{Usuario}') and contraseña='{contraseña}'";

            return (util.ejecutar(query, TipoConsulta.LECTURA));
        }
        [WebMethod]
        public void AgregarCliente(string Nombre, string telefono, string correo, string direccion, string contraseña)
        {

            string query = "INSERT INTO `cliente` (`nombre`, `telefono`,  `correo_electronico`, `direccion`, `contraseña`)"
                + $"VALUES ( '{Nombre}', '{telefono}',  '{correo}', '{direccion}','{contraseña}')";


            util.ejecutar(query, TipoConsulta.ESCRITURA);

        }
        [WebMethod]
        public void ActualizarCliente(int id, string nombre, string telefono, string correo, string direccion, string contraseña)
        {
            string query = "UPDATE `cliente` SET " +
               $"`nombre` = '{nombre}', " +
               $"`telefono` = '{telefono}', " +
               $"`correo_electronico` = '{correo}'," +
               $" `direccion` = '{direccion}', " +
               $"`contraseña` = '{contraseña}'" +
               $" WHERE `cliente`.`id` = {id};";

            util.ejecutar(query, TipoConsulta.ESCRITURA);
        }
        [WebMethod]
        public object[][] ListaProductos(string nombre)
        {
            string query = $"SELECT * FROM `producto` where nombre like '%{nombre}%'";
            return (util.ejecutar(query, TipoConsulta.LECTURA));
        }
        [WebMethod]
        public void AgregarProducto(string nombre, int precio, string codigo, string marca, int stock)
        {
            string query = "INSERT INTO `producto` ( `nombre`, `precio`, `codigo`, `marca`, `stock`) VALUES "
                + $"( '{nombre}', '{precio}', '{codigo}', '{marca}', '{stock}');";
            util.ejecutar(query, TipoConsulta.ESCRITURA);

        }
        [WebMethod]
        public object[][] InfoProducto(int id){
            string query = $"select * from producto where id = {id}";
            return (util.ejecutar(query, TipoConsulta.LECTURA));
        }
        [WebMethod]
        public void EditarProducto(int id, string nombre, int precio, string codigo, string marca, int stock)
        {
            string query = "UPDATE `producto` SET " +
                $"`nombre` = '{nombre}', " +
                $"`precio` = '{precio}', " +
                $"`codigo` = '{codigo}'," +
                $" `marca` = '{marca}', " +
                $"`stock` = '{stock}'" +
                $" WHERE `producto`.`id` = {id};";

            util.ejecutar(query, TipoConsulta.ESCRITURA);
        }
        [WebMethod]
        public object[][] HistorialCompras(int idCliente)
        {
            string query = "select * from venta where id_usuario = " + idCliente.ToString();

            return util.ejecutar(query, TipoConsulta.LECTURA);
        }
        [WebMethod]
        public void Vender(int id, int total, string ticket , string productos)
        {
            string[] producto = productos.Split(':');

            for (int i = 0; i < producto.Length; i=i+2)
            {
                string cant = producto[i];
                string idP = producto[i+1];

                string Query = $"UPDATE `producto` SET `stock` = stock - {cant} WHERE `producto`.`id` = {idP};";
                util.ejecutar(Query, TipoConsulta.ESCRITURA);
            }
            string query = $"INSERT INTO `venta` ( `id_usuario`, `total`, `contenido`) VALUES ( '{id}', '{total}', '{ticket}')";
            util.ejecutar(query, TipoConsulta.ESCRITURA);
        }

       
    }
}