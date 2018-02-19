package ideablog.dao;

import ideablog.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    User selectUserById(long id);
    List<User> selectAllUsers();
    User selectUserByEmail(String email);
    User selectUser(@Param("userName") String userName, @Param("password") String password);
    Boolean insertUser(@Param("userName") String userName, @Param("password") String password, @Param("email") String email, @Param("regTime") String regTime);
    Boolean updatePasswordByUserName(@Param("userName") String userName, @Param("password") String password);
    Boolean updateInfoById(@Param("id") long id, @Param("gender") int gender, @Param("age") int age, @Param("province") String province, @Param("city") String city, @Param("tel") String tel);
    Boolean increaseFollowsById(long id);
    Boolean decreaseFollowsById(long id);
    Boolean switchStatusById(@Param("id") long id, @Param("status") int status);
    Boolean deleteUserById(long id);
}
