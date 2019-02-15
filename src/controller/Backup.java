package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Backup {
    private static String CurrentDate(String initials, String type) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHHmm");
        String dateString = format.format( new Date() );
        //Date date = format.parse ( "2018-12-30" );
        String result = dateString.substring(0, 10)+"_"+initials.trim().toUpperCase()+"_"+type.trim()+"_"+dateString.substring(dateString.length()-4);
        result = result.replace(" ", "_");
        return result;
    }

    private static String filename(String type) {
        switch (type) {
            case "1": return "";
            case "2": return "";
            case "3": return "";
            case "4": return "";
            case "5": return "";
        }
        return type;
    }

    public static String getInitials() {
        try {
            String fullname = System.getProperty("user.name");
            fullname = fullname.replace("-", " ");
            fullname = fullname.replace(".", " ");
            String initials = "";
            String[] names = fullname.split(" ");
            for(String i : names)
                initials+=i.charAt(0);

            return initials;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String backup(String initials, String type) {
        try {
            Path newPath = Paths.get("Z:\\IT\\RTP\\Production\\LiveRTP\\"+CurrentDate(initials, type));
            Files.createDirectories(newPath);
            File source = new File("Z:\\IT\\RTP\\Production\\LiveUploadDownload\\"+filename(type));
            File dest = new File(newPath+"\\"+filename(type));
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
            return dest.getPath().toString();
        } catch (ParseException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
