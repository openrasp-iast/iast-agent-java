import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

/**
 * @author: anyang
 * @create: 2019/03/25 17:53
 */
public class LinuxWeblogicUninstallerTest {
    @Test
    public void testGetInstallPath() {
        try {
            Class clazz = com.baidu.rasp.uninstall.linux.WeblogicUninstaller.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("Weblogic", "/home/weblogic/wls12213/user_projects/domains/base_domain/".replace("/", File.separator));
            String path = Utils.invokeStringMethod(installer, "getInstallPath", new Class[]{String.class},
                    "/home/weblogic/wls12213/user_projects/domains/base_domain");

            assertEquals(path, "/home/weblogic/wls12213/user_projects/domains/base_domain/rasp/");
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testGetScript() {
        try {
            Class clazz = com.baidu.rasp.uninstall.linux.WeblogicUninstaller.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("Weblogic", "/home/weblogic/wls12213/user_projects/domains/base_domain/".replace("/", File.separator));
            String path = Utils.invokeStringMethod(installer, "getScript", new Class[]{String.class},
                    "/home/weblogic/wls12213/user_projects/domains/base_domain/rasp".replace("/", File.separator));
            assertEquals(path, "/home/weblogic/wls12213/user_projects/domains/base_domain/bin/startWebLogic.sh".replace("/", File.separator));
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testRecoverStartScript() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/startWebLogic.sh");
            String content = IOUtils.toString(is, "UTF-8");
            Class clazz = com.baidu.rasp.uninstall.linux.WeblogicUninstaller.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("Weblogic", "/home/weblogic/wls12213/user_projects/domains/base_domain/");
            Utils.invokeStringMethod(installer, "recoverStartScript", new Class[]{String.class}, content);
        } catch (Exception e) {
            //
        }
    }
}
