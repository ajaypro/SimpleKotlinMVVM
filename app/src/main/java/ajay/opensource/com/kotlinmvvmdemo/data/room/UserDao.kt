package ajay.opensource.com.kotlinmvvmdemo.data.room

import ajay.opensource.com.kotlinmvvmdemo.data.model.User
import androidx.room.*
import androidx.room.OnConflictStrategy.FAIL

@Dao
interface UserDao {
    @Insert(onConflict = FAIL)
    fun insertUser(user: User)

    @Query("SELECT * FROM user where email= :email and password= :password")
    fun getUser(email: String, password: String): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUser()

}