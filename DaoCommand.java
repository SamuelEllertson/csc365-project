public interface DaoCommand {
  Object execute(DaoManager daoManager);
} //what is the point of this? Probably needed when we actually 
// create transactions using prepared statements, but if that's the case
// why not just make it preparedstatemnts tho?