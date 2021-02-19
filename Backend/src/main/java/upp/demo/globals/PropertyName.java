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
		String BETA_READERS = "betaReaders";
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
		String CHOSEN_EDITORS = "chosenEditors";
		String NOTES = "notesList";
		String COMMITTEE_MEMBERS = "committee";
		String PLAGIARISM_DECISION = "plagiarismDecision";
		String FINISH_DECISION = "finishDecision";
	}

	interface BookPublishing {
		String BOOK_OWNER_EMAIL = "bookOwnerEmail";
		String WANNA_READ_HANDWRITING = "isAcceptRead";
		String IS_ACCEPTED_TEXT = "isAcceptedText";
		String IS_ACCEPTED_BETA = "isBetaChose";
		String CHOSEN_BETA_READERS = "chosenBeta";
		String BETA_COMMENTS = "betaComments";
		String NEED_LECTOR = "needLector";
		String MORE_CORRECTION = "moreCorrection";
		String PUBLISH_BOOK = "publishBook";
	}

	interface Validation {
		String EMAIL = "email";
	}

	interface Payment {
		String PC_URL = "http://localhost:4201/choose/subscription";
		String MERCHANT_ID = "84074cf2-3d74-11eb-9d51-0242ac130002";
		String FREQUENCY = "MONTH";
		String INTERVAL = "1";
		String CYCLES = "0";
		String SUBSCRIPTION_COST = "4.99";
	}
}
