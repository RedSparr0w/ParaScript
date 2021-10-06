package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;

import java.io.*;

public class UpdateBank implements Strategy {
    private static String seperator = System.getProperty("file.separator");

    @Override
    public boolean activate() {
        if (Bank.isOpen()) {
            Variables.setStatus("Saving Bank");
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        Variables.bank_items = Bank.getBankItemIDs();
        Time.sleep(500);
        saveBankFile();
    }

    private static String getfileName(){
        return "." + seperator + "bank" + seperator + Variables.getAccountUsername() + ".bank";
    }

    public void saveBankFile(){
        String filePath = getfileName();
        checkOrCreateFile(filePath);
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(filePath)
            );
            out.writeObject(Variables.bank_items);
            out.flush();
            out.close();
        } catch(Exception O_o){
            System.out.println("Save file error");
        }
    }

    public static int[] loadBankFile(){
        if (Variables.getAccountUsername().length() <= 0)
            return new int[]{};

        String filePath = getfileName();
        checkOrCreateFile(filePath);
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(filePath)
            );
            int[] array = (int[]) in.readObject();
            in.close();
            return array;
        } catch(Exception O_o){
            System.out.println("Load file error");
        }
        return new int[]{};
    }

    private static void checkOrCreateFile(String filename){
        try {
            File file = new File(filename);
            File directory = new File(file.getParentFile().getPath());
            directory.mkdirs();
            file.createNewFile();
        } catch(Exception O_o){
            System.out.println("Create file error");
        }
    }
}