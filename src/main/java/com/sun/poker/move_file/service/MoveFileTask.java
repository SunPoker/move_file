package com.sun.poker.move_file.service;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.poker.move_file.utils.DateUtil;

@Component
public class MoveFileTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String CEB_IN_DIDR =
            "C:" + File.separator + "Data" + File.separator + "GuangzhouTranslate" + File.separator
                    + "cebin" + File.separator;

    private final static String SEND_TEMP_DIR =
            "C:" + File.separator + "Data" + File.separator + "GuangzhouTranslate" + File.separator
                    + "cebin" + File.separator + "sendTemp";

    private final static String ERROR_DIR =
            "C:" + File.separator + "Data" + File.separator + "GuangzhouTranslate" + File.separator
                    + "cebin" + File.separator + "error";

    //@Scheduled(cron = "*/1 * * * * *")
    @Scheduled(cron = "0 0/5 * * * ?")
    public void moveFile() {
        logger.info("=====>>>>>使用cron  {}", System.currentTimeMillis());
        String dateErrorDir =
                ERROR_DIR + File.separator + DateUtil.dateToStr(new Date(), "yyyyMMdd");

        File errorStartPath = new File(dateErrorDir);
        if (errorStartPath.exists()) {
            startMoveFile(dateErrorDir, CEB_IN_DIDR);
        }
        startMoveFile(SEND_TEMP_DIR, CEB_IN_DIDR);
    }

    private void startMoveFile(String startDir, String endDir) {
        try {
            File StartPath = new File(startDir);
            File endPath = new File(endDir);
            File[] files = StartPath.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (file.renameTo(new File(endPath + File.separator + file.getName()))) {
                        System.out.println("File is moved successful!");
                        logger.info("move file success ！filename：《{}》 endDir：{}", file, endPath);
                    }
                    else {
                        System.out.println("File is failed to move!");
                        logger.info("move file success ！filename：《{}》 startDir：{}", file,
                                StartPath);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("move file error", e);
        }
    }

}
