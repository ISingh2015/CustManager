package com.custmanager.reports;

/**
 *
 * @author Inderjit
 */
public class ReportsDir {

    private static ReportsDir m_clsReportsDir;

    private ReportsDir() {
    }

    private synchronized static ReportsDir getInstance() {
        if (m_clsReportsDir == null) {
            m_clsReportsDir = new ReportsDir();
        }

        return m_clsReportsDir;
    }

    /**
     *
     * return the image path with the additional values
     *
     */
    public static synchronized String getReportPath(String istrAdditionalPath) {
        ReportsDir clsReportsDir = ReportsDir.getInstance();
        String filePath = clsReportsDir.getClass().getResource(istrAdditionalPath).getFile();
        return filePath;
    }
}
