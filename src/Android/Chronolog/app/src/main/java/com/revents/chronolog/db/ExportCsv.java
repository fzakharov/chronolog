package com.revents.chronolog.db;

import com.revents.chronolog.model.Fact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// TODO: 30.01.2017 clean code
public class ExportCsv {

    private SimpleDateFormat mDateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private List<Fact> mFacts;

    public void exportClick() throws IOException {
        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Chronolog.csv";
        FileWriter fw = new FileWriter(csv);

        StringBuilder sb = new StringBuilder();
        sb
                .append("FactId").append(",")
                .append("Timestamp").append(",")
                .append("FactDate").append(",")
                .append("LongValue").append(",")
                .append("StrValue").append(",")
                .append("FactTypeId").append(",")
                .append("FactTypeName").append(",")
                .append("FactTypeDescription").append(",")
                .append("\n");

        fw.write(sb.toString());

        for (int r = 0; r < mFacts.size(); r++) {
            Fact f = mFacts.get(r);

            sb = new StringBuilder();

            sb
                    .append(f.getId()).append(",")
                    .append(date2String(f.getTimestamp())).append(",")
                    .append(date2String(f.getFactDate())).append(",")
                    .append(f.getLongValue()).append(",")
                    .append(f.getStrValue()).append(",")

                    .append(f.getFactType().getId()).append(",")
                    .append(f.getFactType().getName()).append(",")
                    .append(f.getFactType().getDescription()).append(",")

                    .append("\n");

            fw.write(sb.toString());
        }

        fw.close();
    }

    String date2String(Date date) {
        return mDateTimeFormat.format(date);
    }
}
