package com.timbuchalka;

import java.util.*;

public class Main {

    private static List<Album> albums = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D", 5.32);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        List<Song> playList = new ArrayList<>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);
        albums.get(0).addToPlayList(8, playList);
        albums.get(0).addToPlayList(3, playList);
        albums.get(0).addToPlayList(2, playList);
        albums.get(0).addToPlayList(24, playList);

        play(playList);

    }

    private static void play(List<Song> playList) {
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> songLinkedList = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        }

        System.out.println("Now playing " + songLinkedList.next());
        printMenu();

        while (!quit) {
            System.out.println("Enter action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("PlayList complete");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (songLinkedList.hasNext()) {
                            songLinkedList.next();
                        }
                        forward = true;
                    }
                    if (songLinkedList.hasNext()) {
                        System.out.println("Now playing " + songLinkedList.next());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (songLinkedList.hasPrevious()) {
                            songLinkedList.previous();
                        }
                        forward = false;
                    }
                    if (songLinkedList.hasPrevious()) {
                        System.out.println("Now playing " + songLinkedList.previous());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (songLinkedList.hasPrevious()) {
                            System.out.println("Now replaying " + songLinkedList.previous());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the playlist");
                        }
                    } else {
                        if (songLinkedList.hasNext()) {
                            System.out.println("Now replaying " + songLinkedList.next());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the playlist");
                        }
                    }

                    break;
                case 4:
                    Album.printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0) {
                        songLinkedList.remove();
                        if(songLinkedList.hasNext()) {
                            System.out.println("Now playing "+songLinkedList.next());
                        } else if(songLinkedList.hasPrevious()) {
                            System.out.println("Now playing "+songLinkedList.previous());
                        }
                    }
                    break;
            }

        }
    }

    private static void printMenu() {
        System.out.println("\nPress");
        System.out.println("\t 0-to quit");
        System.out.println("\t 1-to play next song");
        System.out.println("\t 2-to play previous song");
        System.out.println("\t 3-to replay the current song");
        System.out.println("\t 4-print list songs in the play list");
        System.out.println("\t 5-print available actions");
        System.out.println("\t 6-delete current song");
    }


}
