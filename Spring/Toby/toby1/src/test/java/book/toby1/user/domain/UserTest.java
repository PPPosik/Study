package book.toby1.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @BeforeEach
    public void onSetUp() {
        user = new User();
    }

    @Test
    void upgradeLevel() {
        for (Level level : Level.values()) {
            if (level.nextLevel() == null) {
                continue;
            }

            user.setLevel(level);
            user.upgradeLevel();

            assertEquals(user.getLevel(), level.nextLevel());
        }
    }

    @Test
    public void cannotUpgradeLevel() {
        for (Level level : Level.values()) {
            if (level.nextLevel() != null) {
                continue;
            }

            user.setLevel(level);

            assertThrows(IllegalStateException.class, () -> user.upgradeLevel());
        }
    }
}