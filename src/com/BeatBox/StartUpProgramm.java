package com.BeatBox;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StartUpProgramm {
    BuildGUIProgramm gui = new BuildGUIProgramm();

    public void startUp(String name) {

        gui.userName = name;
        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            gui.out = new ObjectOutputStream(sock.getOutputStream());
            gui.in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new StartUpProgramm.RemoteReader());
            remote.start();
        } catch (Exception ex) {
            System.out.println("couldn't connect - you'll have to play alone!");
        }
        gui.setUpMidi();
        gui.buildGUI();
    }

    public class RemoteReader implements Runnable {
        boolean[] checkboxState = null;
        String nameToShow = null;
        Object obj = null;
        public void run(){
            try{
                while((obj = gui.in.readObject()) != null){
                    System.out.println("got an object from server");
                    System.out.println(obj.getClass());
                    nameToShow = (String) obj;
                    checkboxState = (boolean[]) gui.in.readObject();
                    gui.otherSeqsMap.put(nameToShow, checkboxState);
                    gui.listVector.add(nameToShow);
                    gui.incomingList.setListData(gui.listVector);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
