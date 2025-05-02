package k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Registro {
    Campos campos;

    public Registro(){
        this.campos = carregarValores();
    }

    public Campos carregarValores() {
        String comando = "reg query \"HKEY_CURRENT_USER\\Control Panel\\Accessibility\\Keyboard Response\"";
        Campos campos = new Campos(); 

        try {
            Process processo = Runtime.getRuntime().exec(comando);
            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.trim().split("\\s+"); 
                
                if (partes.length >= 3) { 
                    String chave = partes[0]; 
                    String valor = partes[partes.length - 1]; 

                    switch (chave) {
                        case "AutoRepeatDelay":
                            campos.setAutoRepeatDelay(valor);
                            break;
                        case "AutoRepeatRate":
                            campos.setAutoRepeatRate(valor);
                            break;
                        case "BounceTime":
                            campos.setBounceTime(valor);
                            break;
                        case "DelayBeforeAcceptance":
                            campos.setDelayBeforeAcceptance(valor);
                            break;
                        case "Flags":
                            campos.setFlags(valor);
                            break;
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campos;
    }

public static boolean setRegistryValue(String campo, String valor){
	if(campo.equals("AutoRepeatDelay") && Integer.parseInt(valor) < 180) return false;
	
    String comando = String.format(
        "reg add \"HKEY_CURRENT_USER\\Control Panel\\Accessibility\\Keyboard Response\" /v \"%s\" /t REG_SZ /d %s /f",
        campo, valor
    );
    
    try {
        Process processo = Runtime.getRuntime().exec(comando);
        processo.waitFor(); 
        
        if (processo.exitValue() == 0) {
            return true;
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
    return false;
    }

public int flagsRegistro() {
	return Integer.parseInt(campos.getFlags());
	}

public void setCampos(Campos campos) {
	this.campos= campos;
	}
public Campos getCampos() {
	return campos;
	}
}