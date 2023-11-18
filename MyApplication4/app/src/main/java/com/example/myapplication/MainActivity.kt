package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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
import com.example.myapplication.models.Update
import com.example.myapplication.ui.theme.MyApplicationTheme
import okhttp3.ResponseBody
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



// ------------------- FIND by ID ----------------------
//        Button(onClick = {
//            val call = ApiClient.apiService.findAll()
//
//            call.enqueue(object : Callback<List<Product>> {
//                override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                    if (response.isSuccessful) {
//                        val resBody = response.body()
//                        Log.i("Respsta", resBody!!.brandName)
//                    } else {
//                        val error = response.errorBody()
//                        Log.i("Erro", error.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<Product>, t: Throwable) {
//                    Log.i("Catch ERRO", t.message ?: "Unknown error")
//                }
//            })
//        }) {
//            Text(text = "Botao")
//        }



// ---------------------- FIND ALL ----------------------
//        Button(onClick = {
//            val call = ApiClient.apiService.findAll()
//
//            call.enqueue(object : Callback<List<Product>> {
//                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                    if (response.isSuccessful) {
//                        val resBody = response.body()
//                        Log.i("Respsta", resBody.toString())
//                    } else {
//                        val error = response.errorBody()
//                        Log.i("Erro", error.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
//                    Log.i("Catch ERRO", t.message ?: "Unknown error")
//                }
//            })
//        }) {
//            Text(text = "Botao")
//        }



//   ----------------- GET QUANTITY BY ID ------------------
        //should return int
//        Button(onClick = {
//            val id = "6435f268f8ac63da19379255"
//            val call = ApiClient.apiService.getProductQuantity(id)
//
//            call.enqueue(object : Callback<Int> {
//                override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                    if (response.isSuccessful) {
//                        Log.i("Respsta", response.toString())
//                    } else {
//                        val error = response.errorBody()
//                        Log.i("Erro", error.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<Int>, t: Throwable) {
//                    Log.i("Catch ERRO", t.message ?: "Unknown error")
//                }
//            })
//        }) {
//            Text(text = "Botao")
//        }



        Button(onClick = {
            val id = "6435f268f8ac63da19379255"
            val call = ApiClient.apiService.getProductQuantity(id)

            call.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.i("Respsta", response.toString())
                    } else {
                        val error = response.errorBody()
                        Log.i("Erro", error.toString())
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("Catch ERRO", t.message ?: "Unknown error")
                }
            })
        }) {
            Text(text = "Botao")
        }




// -------------------- UPDATE --------------------
// not working yet

//        val listProduto = listOf(Update(id = "6435f268f8ac63da19379255", productStock = "13"),
//            Update(id = "6435f3cbf8ac63da19379256", productStock = "4"))
//
//        val call = ApiClient.apiService.updateProductFields(
//            fields = listProduto
//        )
//
//
//        //Junior deve aplicar para mapear lista de produtos do carrinho para lista com 2 fields
//        //val listProduct = listProduto.map { listaCarrinho-> Update(listaCarrinho.id,listaCarrinho.productStock ) }
//
//
//        Button(onClick = {
//            val call = ApiClient.apiService.updateProductFields(listProduto)
//
//            call.enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val resBody = response.body()
//                        Log.i("Respsta", resBody.toString())
//                    } else {
//                        val error = response.errorBody()
//                        Log.i("Erro bo body", error.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    Log.i("Catch ERRO na call", t.message ?: "Unknown error")
//                }
//            })
//        }) {
//            Text(text = "Botao")
//        }
    }
}

