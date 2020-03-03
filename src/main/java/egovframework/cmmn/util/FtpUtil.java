package egovframework.cmmn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.saro.commons.ftp.FTP;
import me.saro.commons.ftp.FTPS;

public class FtpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * FTP 서버 접속하여 도로대장 DWG파일 가져오기
     */
    public void ftpDownload(Map<String, String> paramMap) {
        try {
            LOGGER.info("=== [START] Schedule ===");

            // FTP Server Info
            // 서버 IP
            String host = EgovProperties.getProperty("ftp.kggis.server");
            // 서버 포트
            int port = Integer.parseInt(EgovProperties.getProperty("ftp.kggis.port"));
            // 서버 로그인 계정
            String user = EgovProperties.getProperty("ftp.kggis.username");
            // 서버 로그인 비밀번호
            String pass = EgovProperties.getProperty("ftp.kggis.password");
            // SFTP 여부
            boolean isUseSecurity = EgovProperties.getProperty("ftp.kggis.use-security").equals("true") ? true : false;
            // mode가 active일 경우 변경(default : passive)
            String mode = EgovProperties.getProperty("ftp.kggis.mode");

            // 서버 DWG파일 디렉토리 경로
            String remotePath = EgovProperties.getProperty("ftp.kggis.remote-dwg-directory-path");
            // 로컬 DWG파일 다운로드 디렉토리 경로
            File localPathFile = new File(EgovProperties.getProperty("ftp.kggis.local-dwg-download-directory-path"));
            // 로컬 디렉토리 없을 경우 생성
            if ( !localPathFile.exists() ) localPathFile.mkdirs();

            LOGGER.info("=== [START] Connected to SFTP Server (" + user + "@" + host + ":" + port + ") ===");

            FTP ftp;
            if ( isUseSecurity ) {  // SFTP 여부
                // SFTP로 접속
                ftp = FTP.openSFTP(host, port, user, pass);
            } else {
                // FTP로 접속
                ftp = FTP.openFTP(host, port, user, pass);
                // mode가 active일 경우 변경(default : passive)
                if ( "active".equals(mode) ) {
                    ((FTPS)ftp).enterLocalActiveMode();
                }
            }

            LOGGER.info("=== [Current Path] " + ftp.path() + " ===");

            String remoteFullPath = "";
            String dwgTypeNm = "";
            String dwgName = "";

            String roadNo = paramMap.get("ROAD_NO");
            String dwgType = paramMap.get("DWG_TYPE");

            if ( "01".equals(dwgType) ) {
                dwgTypeNm = "P";
                dwgName = paramMap.get("DWG_NAME") + ".DWG";
            } else if ( "02".equals(dwgType) ) {
                dwgTypeNm = "U";
                dwgName = paramMap.get("DWG_NAME") + ".DWG";
            } else if ( "03".equals(dwgType) ) {
                dwgTypeNm = "Y";
                dwgName = paramMap.get("DWG_NAME") + ".DWG";
            } else if ( "04".equals(dwgType) ) {
                dwgTypeNm = "CONT";
                dwgName = paramMap.get("CON_NAME") + ".DWG";
            } else if ( "05".equals(dwgType) ) {
                dwgTypeNm = "TOP";
                dwgName = paramMap.get("DWG_NAME") + ".DWG";
            }

            remoteFullPath = remotePath + "/" + roadNo + "/" + dwgTypeNm;

            // 파일 위치로 이동
            ftp.path(remoteFullPath);

            LOGGER.info("=== [Move Path] " + ftp.path() + " ===");

            // 서버 디렉토리 내 파일 다운로드
            downloadFile(ftp, dwgName, localPathFile);

            // FTP 종료
            ftp.close();
            LOGGER.info("=== [END  ] Connected to SFTP Server (" + user + "@" + host + ":" + port + ") ===");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    /**
     * 서버에서 FTP로 파일 받기
     * @param ftp
     * @param downloadFileName
     * @param localPathFile
     */
    private void downloadFile(FTP ftp, String downloadFileName, File localPathFile) {
        // SFTP 여부
        boolean isUseSecurity = EgovProperties.getProperty("ftp.kggis.use-security").equals("true") ? true : false;

        try {
            if ( !localPathFile.exists() ) {
                localPathFile.mkdirs();
            }
            // 로컬 파일 저장 경로
            String fileFullPath = localPathFile + "/" + downloadFileName;

            if ( isUseSecurity ) {
                File df = new File(fileFullPath);
                ftp.recv(downloadFileName, df);
            } else {
                FileOutputStream fout = new FileOutputStream(fileFullPath);
                ((FTPS)ftp).getFtp().retrieveFile(ftp.path() + "/" + downloadFileName, fout);
            }

            LOGGER.info("=== [Download File] " + downloadFileName + " ===");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
