package Records;

public record Command(String name){
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Command command)) {
            return false;
        }
        return this.name.equalsIgnoreCase(command.name());
    }
}
