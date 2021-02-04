package upp.demo.globals;

public interface PropertyName {
	interface Keys {
		String VALIDATION = "constraint";
		String INPUT = "input";
		String CUSTOM = "custom";
	}

	interface CustomNames {
		String GENRES = "genres";
		String EDITORS = "editors";
		String BOOKS = "books";
		String OTHER_BOOKS = "otherBooks";
	}

	interface FormName {
		String FORM_DATA = "formData";
	}

	interface VariableName {
		String VALID = "valid";
		String APPROVE_CODE = "approveCode";
		String REGISTRATION_REQUEST = "registrationId";
		String PROCESS_ID = "processId";
		String GENERIC_EMAIL = "genericEmail";
		String BOOK_SYNOPSIS = "bookSynopsis";
		String EDITORS_FOR_REVIEW_TEXT = "textEditors";
		String BOOKS_FOR_REVIEW = "booksForReviews";
		String LOGGED_USER = "loggedUser";
		String EMAIL_FOR_REVIEW = "emailForReview";
		String REVIEWERS_OPINION = "reviewersOpinion";
		String REVIEW_DTO = "reviewDto";
		String REJECT_COUNTER = "rejectCounter";
	}

	interface Review {
		String MORE_DOCUMENT_COUNTER = "moreDocumentCounter";
	    String ACCEPT = "acceptRegistration";
	    String REJECT = "rejectRegistration";
	    String MORE_DOCUMENTS = "moreDocuments";
	    String REVIEW_LIST = "reviewList";
	    String REVIEWERS_COMMENTS = "reviewersComments";
    }

    interface Plagiarism {
		String ALL_EDITORS = "allEditors";
	}

	interface Validation {
		String EMAIL = "email";
	}
}
