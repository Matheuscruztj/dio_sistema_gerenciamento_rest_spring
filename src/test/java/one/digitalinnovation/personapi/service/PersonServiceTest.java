package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static one.digitalinnovation.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    /* cria um mock */
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    /* cria uma instance de uma classe e injeta os mocks que foram criados com @Mock nessa instancia */
    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        /* cria um DTO */
        PersonDTO personDTO = createFakeDTO();

        /* cria uma instancia */
        Person expectedSavedPerson = createFakeEntity();

        /* se o repositório salvasse uma instancia qualquer da classe Person,
        a sua saída seria essa instancia esperada */
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        /* crio localmente a mesma função que retornaria a mensagem de qua                              ndo o objeto é criado */
        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());

        /* salvo a mensagem de quando o objeto é criado */
        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, succesMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
