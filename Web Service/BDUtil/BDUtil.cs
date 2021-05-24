using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace EjemploWS
{
    public class BDUtil
    {
        
        private string servidor;
        private string basedatos;
        private string usuario;
        private string contraseña;
        public BDUtil(string servidor, string basedatos, string usuario, string contraseña)
        {
            
            this.servidor = servidor;
            this.basedatos = basedatos;
            this.usuario = usuario;
            this.contraseña = contraseña;
        }

        public object[][] ejecutar(string consulta, TipoConsulta tipo)
        {
            string cadena_conexion = "datasource=" + this.servidor + ";Port = 3306";
            cadena_conexion += ";username=" + this.usuario + ";password=" + this.contraseña;
            cadena_conexion += ";database = " + this.basedatos; //Cadena de texto para invocar conexion

            object[][] datos = null;
            
            MySqlConnection mycn = new MySqlConnection(cadena_conexion); //Contiene la conexion a la bd
            MySqlCommand mycm = new MySqlCommand(); //Sirve para hacer la consulta a la bd
            mycm.CommandTimeout = 60; //Se le asigna un limite de tiempo de ejecucion a la consulta 
            MySqlDataAdapter myda = new MySqlDataAdapter();
            DataSet ds = new DataSet();
            DataTable dt;

            try
            {
                mycn.Open();//Se abre la coneccion a la base de datos

                mycm.CommandText = consulta;
                mycm.CommandType = CommandType.Text;
                mycm.Connection = mycn;
                if (tipo == TipoConsulta.LECTURA)
                {
                    myda.SelectCommand = mycm;
                    myda.Fill(ds);
                }
                else
                {
                    mycm.ExecuteNonQuery();
                   
                }
                mycn.Close();

            }
            catch (Exception e)
            {
              
            }


           
            if (tipo == TipoConsulta.LECTURA && !(ds == null))
            {
                dt = ds.Tables[0];
                datos = new object[dt.Rows.Count][];
                for (int i = 0; i < datos.Length; i++)
                {
                    datos[i] = new object[dt.Columns.Count];
                    for (int j = 0; j < datos[i].Length; j++)
                    {
                        datos[i][j] = dt.Rows[i][j];
                    }
                }
            }
            return (datos);
        }
    }
}