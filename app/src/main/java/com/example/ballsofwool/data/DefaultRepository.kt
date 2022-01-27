package com.example.ballsofwool.data

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DefaultRepository: Repository {
    @Inject private lateinit var datasource: DefaultLocalDatasource
}