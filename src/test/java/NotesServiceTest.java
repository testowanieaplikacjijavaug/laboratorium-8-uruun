import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

class NotesServiceImplTest {

    @TestSubject
    NotesServiceImpl ns;

    @Mock
	NotesStorage mock;

	@BeforeEach
	void setup() {
        EasyMockSupport support = new EasyMockSupport();
        mock = support.createMock(NotesStorage.class);
		ns = NotesServiceImpl.createWith(mock);
	}

	@Test
	void test_NotesService_add() {
        Note note = Note.of("Adam", 3);
        mock.add(note);
        expectLastCall();
        replay(mock);
        ns.add(note);
	}

	@Test
	void test_NotesService_averageOf() {
        Note note = Note.of("Adam", 3);
        Note note2 = Note.of("Adam", 4);
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(note);
        notes.add(note2);
        expect(mock.getAllNotesOf("Adam")).andReturn(notes);
        replay(mock);
        assertEquals(3.5f, ns.averageOf("Adam"));
	}
}
