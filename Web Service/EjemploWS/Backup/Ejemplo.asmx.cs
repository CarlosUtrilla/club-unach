using System;
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
    public class Ejemplo : System.Web.Services.WebService
    {
        private BDUtil util = new BDUtil(MotorBD.MSSQLSERVER, "localhost", "ejemplo", "sa", "p700419p780422");
        [WebMethod]
        public object[][] listaCatalogo()
        {
            return(util.ejecutar("select * from catalogo", TipoConsulta.LECTURA));
        }
        [WebMethod]
        public object[][] listaPrincipal()
        {
            return(util.ejecutar("select * from vw_listaprincipal", TipoConsulta.LECTURA));
        }
        [WebMethod]
        public int guardar(int idreg, string nombre, int idcat, int tarea)
        {
            string query = null;
            object[][] result = null;
            if (util.getMotor() == MotorBD.MSSQLSERVER)
            {
                query = "execute sp_RegistroPrincipal " + idreg + ", '" + nombre + "', " + idcat + ", " + tarea;
            }
            else
            {
                query = "call sp_RegistroPrincipal( " + idreg + ", '" + nombre + "', " + idcat + ", " + tarea + " );";
            }
            result = util.ejecutar(query, (tarea == 1 ? TipoConsulta.LECTURA : TipoConsulta.ESCRITURA));
            if (tarea == 1 && !(result == null))
            {
                idreg = int.Parse(result[0][0].ToString());
            }
            return (idreg);
        }
    }
}