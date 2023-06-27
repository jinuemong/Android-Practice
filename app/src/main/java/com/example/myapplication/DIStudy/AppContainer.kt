package com.example.myapplication.DIStudy

import retrofit2.Retrofit

// 앱 전체에 걸쳐 사용할 수 있으므로 모든 액티비티에서 사용할 수 있는 application 클래스에 배치해야 함
// AppContainer 인스턴스를 갖고 있는 Application 클래스를 만들어야 한다.

// Container of objects shared across the whole app
class AppContainer {


    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)

    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    // userRepository is not private; it'll be exposed
    val userRepository = UserRepository(localDataSource, remoteDataSource)

    // 뷰 모델을 인스턴스화
    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}
