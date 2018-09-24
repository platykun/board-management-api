package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.entity.UserEntity;
import boardmanagement.api.demo.manage.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * ルームサービスクラス.
 */
@Service
public class UserService {
    private static final int FIND_LIMIT = 100;

    @NonNull
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * セッション情報からログインユーザを取得する.
     * @return ログインユーザ
     */
    public UserEntityBean getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        UserEntity loginUserEntity = userRepository.findByName(userName);

        return new UserEntityBean(loginUserEntity);
    }

    /**
     * ユーザ名からユーザ情報を取得する.
     * @param userName ユーザ名
     * @return ユーザ情報
     */
    public UserEntityBean findByUserName(String userName) {
        UserEntity loginUserEntity = userRepository.findByName(userName);

        return new UserEntityBean(loginUserEntity);
    }

    /**
     * ユーザ数をカウントする.
     * @return ユーザ数
     */
    public int countByUserName(String name) {
        return userRepository.countByName(name);
    }

    /**
     * ユーザIDからユーザ情報を取得する.
     * @param userId ユーザID
     * @return ユーザ情報
     */
    public UserEntityBean findByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId);

        return new UserEntityBean(userEntity);
    }
}
