package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.dto.UserRegisterDto;
import boardmanagement.api.demo.manage.entity.PasswordEntity;
import boardmanagement.api.demo.manage.entity.UserEntity;
import boardmanagement.api.demo.manage.repository.PasswordRepository;
import boardmanagement.api.demo.manage.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ユーザサービスクラス.
 */
@Service
public class UserService {
    private static final int FIND_LIMIT = 100;

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final PasswordRepository passwordRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordRepository passwordRepository) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    /**
     * セッション情報からログインユーザを取得する.
     * ログインユーザが取得できなかった場合、IllegalArgumentExcepiton.
     * セッションにログインユーザが存在するかどうか不透明な場合、存在チェックをかけていることが前提となる.
     *
     * @return ログインユーザ
     */
    public UserEntityBean findLoginUserFronSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        Optional<UserEntityBean> loginUser = userRepository.findById(userName).map(UserEntityBean::new);
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("cant find login user");
        }

        return loginUser.get();
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

    /**
     * ユーザを作成する.
     * @param user ユーザ情報
     * @return 作成済ユーザ
     */
    public UserEntityBean register(UserRegisterDto user) {

        UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getAuthority(), "", "");
        UserEntity result = userRepository.save(userEntity);

        PasswordEntity passwordEntity = new PasswordEntity(result.getId(), user.getPassword());
        passwordRepository.save(passwordEntity);

        return new UserEntityBean(result);
    }

    /**
     * ログインユーザのアイコン情報を更新する.
     * @param icon アイコン
     * @param iconColor アイコン色
     * @return 更新後のユーザ情報
     */
    public UserEntityBean updateUserIcon(String icon, String iconColor) {
        UserEntityBean loginUser = findLoginUserFronSession();

        UserEntity user = loginUser.toEntity();
        user.setIcon(icon);
        user.setIconColor(iconColor);

        UserEntity result = userRepository.save(user);

        return new UserEntityBean(result);
    }

    /**
     * キーワードをもとにユーザを検索する.
     *
     * @param keyword キーワード
     * @return ユーザ一覧
     */
    public List<UserEntityBean> findLikeId(String keyword) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);

        Page<UserEntity> userEntityPage;
        userEntityPage = userRepository.findLikeId(keyword, limit);

        List<UserEntityBean> users = new ArrayList<>();
        userEntityPage.forEach(r -> users.add(new UserEntityBean(r)));

        return users;
    }

    /**
     * ユーザが作成可能かどうかチェックする.
     * @param userId ユーザID
     * @return 作成可能: ture 作成不可: false
     */
    public Boolean isAvailableUser(String userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return !user.isPresent();
    }
}
