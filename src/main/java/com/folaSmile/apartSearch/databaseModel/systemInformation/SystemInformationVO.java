package com.folaSmile.apartSearch.databaseModel.systemInformation;

public class SystemInformationVO {

    final private String clientVer;
    final private String databaseVer;
    final private String databaseRange;
    final private int dataCase;
    final private String notice;


    public SystemInformationVO(String clientVer, String databaseVer, String databaseRange, int dataCase,
                               String noticeKey,
                               String notice) {


        this.clientVer = clientVer;
        this.databaseVer = databaseVer;
        this.databaseRange = databaseRange;
        this.dataCase = dataCase;

        // THIS PART NEED REFACTORING - Data text should be moved to envClientVer class

        // make new line from db text literal "-newline"
        notice = notice.replace("-newline", "\n");


        // (noticeKey == 1) means Use inner notice message
        // otherwise get notice message from database
        if (noticeKey.equals("1")) {
            this.notice = String.format("""
                    본 버전(%s)은
                    23년 2월 9일 배포판입니다.                              
                    """, this.clientVer);
        } else {
            this.notice = String.format("""
                    본 버전(%s)은
                    23년 2월 9일 배포판입니다.                              
                    """, this.clientVer) + notice;
        }

    }


    public String getClientVer() {
        return clientVer;
    }

    public String getDatabaseVer() {
        return databaseVer;
    }

    public String getDatabaseRange() {
        return databaseRange;
    }

    public int getDataCase() {
        return dataCase;
    }

    public String getNotice() {
        return notice;
    }
}
