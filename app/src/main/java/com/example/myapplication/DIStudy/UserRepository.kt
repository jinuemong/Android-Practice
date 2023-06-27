package com.example.myapplication.DIStudy

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {

}

class UserLocalDataSource {

}
class UserRemoteDataSource(
    private val loginService: LoginService
) {

}