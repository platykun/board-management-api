package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.entity.UserEntity;
import boardmanagement.api.demo.manage.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<UserEntityBean> getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        return  userRepository.findById(userName).map(UserEntityBean::new);
    }

    /**
     * ユーザ名からユーザ情報を取得する.
     * @param userName ユーザ名
     * @return ユーザ情報
     */
    public Optional<UserEntityBean> findByUserName(String userName) {
        return userRepository.findByName(userName).map(UserEntityBean::new);
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
    public Optional<UserEntityBean> findByUserId(String userId) {
        return userRepository.findById(userId).map(UserEntityBean::new);
    }

    /**
     * ユーザ数をカウントする.
     * @param id ID
     * @return ユーザ数
     */
    public int countById(String id) {
        return userRepository.countById(id);
    }
}
