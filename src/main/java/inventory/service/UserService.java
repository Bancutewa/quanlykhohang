package inventory.service;

import inventory.dao.UserDAO;
import inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    final static Logger log = Logger.getLogger(UserService.class);
    @Autowired
    private UserDAO<User> userDAO;
    public List<User> findByProperty(String property, Object value) {
        log.info("Find user by property: " + property + " value: " + value);
        return userDAO.findByProperty(property, value);
    }

}
