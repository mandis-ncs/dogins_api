package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.models.Product
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Remova a linha abaixo, pois você não precisa mais do layout XML
        // setContentView(R.layout.activity_main)

        // Use o setContent para definir o conteúdo da atividade com o Compose
        setContent {
            MyApplicationTheme {
                // A Surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Chame a função Composable que define o conteúdo da tela
                    MyScreenContent()
                }
            }
        }
    }

    @Composable
    fun MyScreenContent() {
        // Use Compose para definir a interface do usuário
        Button(onClick = {
            val id = "6435f268f8ac63da19379255"
            val call = ApiClient.apiService.findById(id)

            call.enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        val resBody = response.body()
                        Log.i("Respsta", resBody!!.brandName)
                    } else {
                        val error = response.errorBody()
                        Log.i("Erro", error.toString())
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.i("Catch ERRO", t.message ?: "Unknown error")
                }
            })
        }) {
            Text(text = "Botao")
        }
    }
}
