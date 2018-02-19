package ideablog.service.impl;

import ideablog.dao.IUserDao;
import ideablog.model.User;
import ideablog.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUserById(long id) {
        return this.userDao.selectUserById(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return this.userDao.selectAllUsers();
    }

    @Override
    public User selectUserByEmail(String email) {
        return this.userDao.selectUserByEmail(email);
    }

    @Override
    public User login(String userName, String password) {
        return this.userDao.selectUser(userName, password);
    }

    @Override
    public Boolean register(String userName, String password, String email, String regTime) {
        return this.userDao.insertUser(userName, password, email, regTime);
    }

    @Override
    public Boolean updatePasswordByUserName(String userName, String password) {
        return this.userDao.updatePasswordByUserName(userName, password);
    }

    @Override
    public Boolean updateInfoById(long id, int gender, int age, String province, String city, String tel) {
        return this.userDao.updateInfoById(id, gender, age, province, city, tel);
    }

    @Override
    public Boolean increaseFollowsById(long id) {
        return this.userDao.increaseFollowsById(id);
    }

    @Override
    public Boolean decreaseFollowsById(long id) {
        return this.userDao.decreaseFollowsById(id);
    }

    @Override
    public Boolean switchStatusById(long id, int status) {
        return this.userDao.switchStatusById(id, status);
    }

    @Override
    public Boolean deleteUserById(long id) {
        return this.userDao.deleteUserById(id);
    }
}