import java.io.*;

//
// Não alterar o nome da classe!
//
class disasm {
    public static void main(String[] args) {
        if (args.length == 0)
        {
            return;
        }
        
        String filename = args[0];
        
        try ( InputStream inputStream = new FileInputStream(filename); )
        {
            int byteRead = -1;
            String[] binary = new String[8];
            String[] byte1 = new String[8];
            String[] byte2 = new String[8];
            String[] twoBytes = new String[16];
            String[] opCode = new String[4];
            String[] instructionsOpCode = {"jns", "load", "store", "add",
                                           "subt", "input", "output", "halt",
                                           "skipcond", "jump", "clear", "addi",
                                           "jumpi", "loadi", "storei"};
            String opCodeString;
            String[] adress = new String[12];
            String adressString;
            String hex;
            boolean binaryTester = true;
            int count = 0;
            int auxConversor;
            int auxConversor2;

            while((byteRead = inputStream.read()) != -1){
                binary = Integer.toBinaryString(byteRead).split("");


                //Coloca os zeros na frente do binario
                if(binaryTester == true){
                    for (int i = 0; i < 8; i++){
                        if(i < 8 - binary.length){
                            byte1[i] = "0";
                        }
                        else{
                            byte1[i] = binary[count];
                            count++;
                        }        
                    }
                    count = 0;
                    binaryTester = false;
                }
                else{
                    for (int i = 0; i < 8; i++){
                        if(i < 8 - binary.length){
                            byte2[i] = "0";
                        }
                        else{
                            byte2[i] = binary[count];
                            count++;
                        }        
                    }
                    count = 0;
                    binaryTester = true;
                }


                if(binaryTester == true){
                //junta o byte1 com o byte2
                    for(int i = 0; i < twoBytes.length; i++){
                        if(i <= 7){
                            twoBytes[i] = byte1[i];
                        }
                        else{
                            twoBytes[i] = byte2[count];
                            count++;
                        }
                    }
                    count = 0;
                
                    //quebra o binario no opCode e o endereço
                    for(int i = 0; i < twoBytes.length; i++){
                        if(i <= 3){
                            opCode[i] = twoBytes[i];
                        }
                        else{
                            adress[count] = twoBytes[i];
                            count++;
                            // System.out.print(count);
                        }
                    }

                    count = 0;
                    opCodeString = opCode[0] + "" + opCode[1] + "" +  opCode[2] + "" +  opCode[3];
                    auxConversor2 = Integer.parseInt(opCodeString, 2);

                    // System.out.print(instructionsOpCode[auxConversor2] + " ");

                    adressString = adress[0] + "" + adress[1]  + "" + adress[2] + "" +  
                                    adress[3] + "" + adress[4]  + "" + adress[5] + "" +  
                                    adress[6] + "" + adress[7]  + "" + adress[8] + "" +  
                                    adress[9] + "" + adress[10] + "" + adress[11];
                    auxConversor = Integer.parseInt(adressString, 2);
                    hex = Integer.toHexString(auxConversor);
                    if(auxConversor2 == 5 || auxConversor2 == 6 || auxConversor2 == 7 || auxConversor2 == 10){
                         System.out.println(instructionsOpCode[auxConversor2]);
                    }
                    else{
                    System.out.println(instructionsOpCode[auxConversor2] + " "  + hex);
                    }
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
