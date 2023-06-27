package com.example.myapplication.DIStudy

// 팩토리 정의 ( 뷰 모델을 인스턴스화 하기 위함 )
// 객체를 생성하기 위한 인터페이스를 만들고, 인터페이스를 구현하는 클래스에서 어떤 객체를 만들지 결정하는 패턴
// 객체를 만들기 위한 인터페이스를 선언해서 유연하게 구현해 사용할 수 있도록 하는 패턴
interface Factory<T> {
    fun create(): T
}



class LoginViewModelFactory(private val userRepository: UserRepository) : Factory<LoginViewModel>{
    override fun create() : LoginViewModel{
        return LoginViewModel(userRepository = userRepository)
    }
}