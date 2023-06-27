package com.example.myapplication.DIStudy

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit


// 로그인 액티비티 1 -> 보일러 플레이트 발생 + 재사용성 떨어짐
//class LoginActivity: Activity() {
//
//    private lateinit var loginViewModel: LoginViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // In order to satisfy the dependencies of LoginViewModel, you have to also
//        // satisfy the dependencies of all of its dependencies recursively.
//        // First, create retrofit which is the dependency of UserRemoteDataSource
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://example.com")
//            .build()
//            .create(LoginService::class.java)
//
//        // Then, satisfy the dependencies of UserRepository
//        val remoteDataSource = UserRemoteDataSource(retrofit)
//        val localDataSource = UserLocalDataSource()
//
//        // Now you can create an instance of UserRepository that LoginViewModel needs
//        val userRepository = UserRepository(localDataSource, remoteDataSource)
//
//        // Lastly, create an instance of LoginViewModel with userRepository
//        loginViewModel = LoginViewModel(userRepository)
//    }
//}

// 로그인 액티비티 2 - > 재사용 성을 증가시키는 컨테이너 제작
// 싱글톤으로 구현하지 않고 모든 액티비티에게 공유되는 AppContainer를 통해서 UserRepository를
// 필요로 하는 모든 액티비티에 인스턴스를 제공할 수 있음
class LoginActivity: Activity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gets userRepository from the instance of AppContainer in Application
        val appContainer = (application as MyApplication).appContainer
        // 미리 생성해둔 로그인 팩토리를 통해서 뷰 모델에 접근
        loginViewModel = appContainer.loginViewModelFactory.create()
    }
}