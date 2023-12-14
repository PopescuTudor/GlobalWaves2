# Proiect GlobalWaves  - Etapa 2
## Popescu Tudor-Cristian 334CD



## Skel Structure

* src/
  * audio 
    * Collections/ - contains the classes for audio file collections
      * Album - contains the information about an album. It also contains methods for printing info or retrieving likes.
      * AlbumOutput - contains only the essential information about an album that will be written to the output of certain commands
      * [Abstract] AudioCollection - abstract class that contains the common methods for all audio collections
      * Playlist - contains the information about a playlist. It also contains methods for filtering, switching visibility and managing followers
      * PlaylistOutput - contains only the essential information about a playlist that will be written to the output of certain commands
      * Podcast - contains the information about a podcast. It also contains methods for printing info; it overrides methods from AudioCollection just like every other collection class, in order to retrieve track info
      * PodcastOutput - contains only the essential information about a podcast that will be written to the output of certain commands
    * Files/ - contains the classes for audio files
      * [Abstract] AudioFile - abstract class that contains the common attribute: duration
      * Episode - contains the distinct information about an episode: description
      * Song - contains the distinct information about a song. It also contains methods for filtering
    * [Abstract] LibraryEntry - abstract class that contains the common attribute: name, and definition for the methods (for filtering) that will be overridden in the subclasses
    * community/ - contains classes for everything that would be posted by an artist/host targeted to the community
      * Announcement - contains the information about an announcement. It also contains methods for printing info
      * Event - contains the information about an event. It also contains methods for printing info
      * Merch - contains the information about a merch. It also contains methods for printing info
    * Player/ 
      * Player - a more complex class that contains the information about a player. It also handles setting the source, repeat mode, shuffle functionality etc. Most importantly, it simulates the passing of time, an essential aspect of this app.
      * PlayerSource - class that retains info about the current source of the player: type, remained duration and whether or not it is from a collection are essential. More constructors are used for different types of sources. It also handles the underlying functionality of shuffle, skip, next, prev.
      * PlayerStats - class used to output player status
      * PodcastBookmark - when playing a podcast, the player will remember the last played episode and the time it was paused at
    * searchBar/
      * Filters - all possible filter inputs
      * FilterUtils - handles the underlying functionality of filtering by certain criteria
      * SearchBar - class that handles the search bar functionality. All files have certain attributes that are used for filtering. It also handles selecting by ID.
    * user/ - in this stage of the project, there are 3 types of possible users + admin (which is not a user, and as such, it is not included in this package)
      * Artist - contains the information about an artist, most importantly the name, the albums, the events, the merch. It also contains methods for printing page info, adding/removing albums and its songs, adding/removing events, adding/removing merch.
      * Host - contains info about the host: name, podcasts, announcements. It also contains methods for printing page info, adding/removing podcasts, adding/removing announcements.
      * User - a looooong class handling most normal user interaction with the app. From printing the page he is on, to modifying the status of its unique player and playlist functionality, it is all called from here.
    * utils/
      * Enum - contains all enums used in the project
    * [SINGLETON] Admin - class that retains a database of all users and audio files, as such, it is a singleton. An important part of this stage of the project is adding/removing users, which is handled here (including the checks for the possibility of removing)
    * CommandRunner - another long class that receives (in all of its methods) the CommandInput from main for all commands, checks if the user is online and then calls the necessary methods. Finally, it creates the output.
    * checker/ - checker files
    * fileio/ - contains classes used to read data from the json files
    * main/
        * Main - the Main class runs the checker. Also it contains a switch for the type of command received from the input file
        * Test - run the main method from Test class with the name of the input file from the command line and the result will be written
          to the out.txt file. Thus, you can compare this result with ref.
* input/ - contains the tests and library in JSON format
* ref/ - contains all reference output for the tests in JSON format


## Design pattern used: ... ✨ Singleton ✨ 
