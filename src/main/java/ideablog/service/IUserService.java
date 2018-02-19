package ideablog.service;

import ideablog.model.User;

import java.util.List;

public interface IUserService {

    User selectUserById(long id);
    List<User> selectAllUsers();
    User selectUserByEmail(String email);
    User login(String userName, String password);
    Boolean register(String userName, String password, String email, String regTime);
    Boolean updatePasswordByUserName(String userName, String password);
    Boolean updateInfoById(long id, int gender, int age, String province, String city, String tel);
    Boolean increaseFollowsById(long id);
    Boolean decreaseFollowsById(long id);
    Boolean switchStatusById(long id, int status);
    Boolean deleteUserById(long id);
}