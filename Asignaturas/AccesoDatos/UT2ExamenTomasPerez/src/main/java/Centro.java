public class Centro {


    private String CodCen;
    private int CodEmpDir;
    private String NomCen;
    private String DirCen;
    private String PobCen;
    private String nomDirector;


    public Centro(String codCen, int codEmpDir, String nomCen, String dirCen, String pobCen, String nomDirector1) {
        CodCen = codCen;
        CodEmpDir = codEmpDir;
        NomCen = nomCen;
        DirCen = dirCen;
        PobCen = pobCen;
        this.nomDirector = nomDirector1;

    }

    public String getCodCen() {
        return CodCen;
    }

    public void setCodCen(String codCen) {
        CodCen = codCen;
    }

    public int getCodEmpDir() {
        return CodEmpDir;
    }

    public void setCodEmpDir(int codEmpDir) {
        CodEmpDir = codEmpDir;
    }

    public String getNomCen() {
        return NomCen;
    }

    public void setNomCen(String nomCen) {
        NomCen = nomCen;
    }

    public String getPobCen() {
        return PobCen;
    }

    public void setPobCen(String pobCen) {
        PobCen = pobCen;
    }

    public String getDirCen() {
        return DirCen;
    }

    public void setDirCen(String dirCen) {
        DirCen = dirCen;
    }

    public String getNomDirector() {
        return nomDirector;
    }

    public void setNomDirector(String nomDirector) {
        nomDirector = nomDirector;
    }

    @Override
    public String toString() {
        return "Centro:" + CodCen + " - " + NomCen + " | Director: " + nomDirector;
    }
}
