package exemplo.curso1.model;

public class Professor {
    private int professorID;
    private String nome;
    private int idade;

    public Professor() {

    }

    public Professor(int professorID, String nome, int idade) {
        this.professorID = professorID;
        this.nome = nome;
        this.idade = idade;
    }

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Professor(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "professorID=" + professorID +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
