package com.msu.DTO;

public class SuggestionRequestDTO {
	
	   private boolean createNew;
	    private Long professorId;
	    
	    public boolean isCreateNew() {
	        return createNew;
	    }
	    
	    public void setCreateNew(boolean createNew) {
	        this.createNew = createNew;
	    }
	    
	    public Long getProfessorId() {
	        return professorId;
	    }
	    
	    public void setProfessorId(Long professorId) {
	        this.professorId = professorId;
	    }
	
	
	

}
