import java.util.List;

//Interfejs obsługujący przechowywanie danych
public interface NotesStorage {
	List<Note> notes = null;
	void add(Note note);
	List<Note> getAllNotesOf(String name);
	void clear();
}
