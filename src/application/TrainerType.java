package application;

public class TrainerType 
{
    private int trainerId;
    private String typeId;

    // constructor
    public TrainerType()
    {
        this.trainerId = 0;
        this.typeId = "no type";
    }

    // constructor overload
    public TrainerType(int _trainerId, String _typeId)
    {
        this.setTrainerid(_trainerId);
        this.setTypeId(_typeId);
    }

    // setters
    public void setTrainerid(int _trainerId)
    {
        this.trainerId = _trainerId;
    }

    public void setTypeId(String _typeId)
    {
        this.typeId = _typeId;
    }

    // getters
    public int getTrainerId()
    {
        return this.trainerId;
    }

    public String getTypeId()
    {
        return this.typeId;
    }
}
