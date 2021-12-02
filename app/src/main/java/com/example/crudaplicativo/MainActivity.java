package com.example.crudaplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Nome= (TextView) findViewById(R.id.edittextNome);
        TextView CPF= (TextView) findViewById(R.id.edittextCPF);
        TextView Telefone= (TextView) findViewById(R.id.edittextTelefone);
        TextView Tipo= (TextView) findViewById(R.id.edittextTipo);
        TextView Endereco= (TextView) findViewById(R.id.edittextEndereco);
        TextView Numero= (TextView) findViewById(R.id.edittextNumero);
        TextView Cep= (TextView) findViewById(R.id.edittextCep);
        TextView Bairro= (TextView) findViewById(R.id.edittextBairro);
        TextView Cidade= (TextView) findViewById(R.id.edittextCidade);
        TextView Estado= (TextView) findViewById(R.id.edittextEstado);

        Button btninsert=(Button) findViewById(R.id.btnadd);
        Button btnupdate=(Button) findViewById(R.id.btnupdate);
        Button btndelete=(Button) findViewById(R.id.btndelete);
        Button btnget=(Button) findViewById(R.id.btnget);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= connectionclass();
                try{
                    if (connection !=null){
                        String sqlinsert="Insert into UserInfo_Tab values ('" +CPF.getText().toString()+"','"+Nome.getText().toString()+"','"+Telefone.getText().toString()+"','"+Tipo.getText().toString()+"','"+Endereco.getText().toString()+"','"+Numero.getText().toString()+"','"+Cep.getText().toString()+"','"+Bairro.getText().toString()+"','"+Cidade.getText().toString()+"','"+Estado.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }

                }
                catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= connectionclass();
                try{
                    if (connection !=null){
                        String sqlinsert="update UserInfo_Tab set Nome ='"+Nome.getText().toString()+"', Telefone = '"+Telefone.getText().toString()+"', Tipo ='"+Tipo.getText().toString()+"', Endereco ='"+Endereco.getText().toString()+"', Numero ='"+Numero.getText().toString()+"', Cep ='"+Cep.getText().toString()+"', Bairro ='"+Bairro.getText().toString()+"', Cidade ='"+Cidade.getText().toString()+"', Estado = '"+Estado.getText().toString()+"' where CPF = '" +CPF.getText().toString() + "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }

                }
                catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= connectionclass();
                try{
                    if (connection !=null){
                        String sqldelete="delete UserInfo_Tab where CPF = '" +CPF.getText().toString()+ "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqldelete);
                    }

                }
                catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= connectionclass();
                try{
                    if (connection !=null){
                        String sqldelete="select * from UserInfo_Tab where CPF = '" +CPF.getText().toString()+ "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqldelete);

                        while (rs.next()){
                            Nome.setText(rs.getString(2));
                            Endereco.setText(rs.getString(3));
                        }
                    }

                }
                catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });




    }
    @SuppressLint("NewApi")
    public Connection connectionclass(){
        Connection con=null;
        String ip="172.29.192.1", port="49682", username="sa", password="teste", databasename="CRUDAndroidDB";
        StrictMode.ThreadPolicy tp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            con= DriverManager.getConnection(connectionUrl);

        }
        catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
        return con;
    }
}