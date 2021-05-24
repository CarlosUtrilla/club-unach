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
        private MotorBD motor;
        private string servidor;
        private string basedatos;
        private string usuario;
        private string contraseña;
        public BDUtil(MotorBD motor, string servidor, string basedatos, string usuario, string contraseña)
        {
            this.motor = motor;
            this.servidor = servidor;
            this.basedatos = basedatos;
            this.usuario = usuario;
            this.contraseña = contraseña;
        }
        public MotorBD getMotor()
        {
            return (this.motor);
        }
        public object[][] ejecutar(string consulta, TipoConsulta tipo)
        {
            string cadena_conexion = "";
            object[][] datos = null;
            SqlConnection mscn = new SqlConnection();
            SqlCommand mscm = new SqlCommand();
            SqlDataAdapter msda = new SqlDataAdapter();
            MySqlConnection mycn = new MySqlConnection();
            MySqlCommand mycm = new MySqlCommand();
            MySqlDataAdapter myda = new MySqlDataAdapter();
            DataSet ds = new DataSet();
            DataTable dt = new DataTable();
            cadena_conexion = "Data Source=" + this.servidor + ";Initial Catalog=" + this.basedatos;
            cadena_conexion += ";Uid=" + this.usuario + ";Pwd=" + this.contraseña;
            if (this.motor == MotorBD.MYSQL)
            {
                cadena_conexion = "Server=" + this.servidor + ";Database=" + this.basedatos;
                cadena_conexion += ";Uid=" + this.usuario + ";Pwd=" + this.contraseña;
                mycn.ConnectionString = cadena_conexion;
                mycn.Open();
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
            else
            {
                mscn.ConnectionString = cadena_conexion;
                mscn.Open();
                mscm.CommandText = consulta;
                mscm.CommandType = CommandType.Text;
                mscm.Connection = mscn;
                if (tipo == TipoConsulta.LECTURA)
                {
                    msda.SelectCommand = mscm;
                    msda.Fill(ds);
                }
                else
                {
                    mscm.ExecuteNonQuery();
                }
                mscn.Close();
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