package org.test.testutest

import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.test.testutest.data.db.AppDataBase
import org.test.testutest.data.db.dao.UserDao
import org.test.testutest.data.models.User
import org.test.testutest.data.net.AppApi
import org.test.testutest.data.preferences.UserSession
import org.test.testutest.ui.MainViewModel
import org.test.testutest.util.TestServer
import org.test.testutest.util.TrampolineSchedulerRule
import org.test.testutest.util.ViewModelRule
import org.mockito.Mockito.`when` as mWhen


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val trampolineSchedulerRule: TrampolineSchedulerRule = TrampolineSchedulerRule()

    @Rule
    @JvmField
    val vm: ViewModelRule = ViewModelRule()

    @Mock
    lateinit var userDao: UserDao

    lateinit var viewModel: MainViewModel

    @Before
    fun prepare() {

        mWhen(vm.appDataBase.userDao()).thenReturn(userDao)

        UserSession.init(vm.context)
        AppDataBase.db = vm.appDataBase
        AppApi.init(TestServer.httpUrl)

        viewModel = MainViewModel()

    }

    @Test
    fun login_whenIsSuccess() {
        viewModel.login("testUser", "12345")
                .test()
                .assertValue("abc")
    }

    @Test
    fun login_whenNoSuccess() {
        viewModel.login("testUSer2", "45677")
                .test()
                .assertError { it.message == "Usuario o password incorrectos" }
    }

    @Test
    fun getUser_whenIsSaved() {
        val user = User("3", "Hola", "Role")
        user.id = 1

        mWhen(userDao.getUser()).thenReturn(Maybe.just(user))
        mWhen(vm.preferences.getString("token", null)).thenReturn("abc")
        mWhen(vm.preferences.getString("id", null)).thenReturn("2")

        viewModel.getUser()
                .test()
                .assertResult(user)

    }

    @Test
    fun getUser_whenNoSaved() {
        mWhen(userDao.getUser()).thenReturn(Maybe.empty())
        mWhen(vm.preferences.getString("token", null)).thenReturn("abc")
        mWhen(vm.preferences.getString("id", null)).thenReturn("2")

        viewModel.getUser()
                .test()
                .assertValue { it.idRemote == "2" }
    }


}
