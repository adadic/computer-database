package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import dto.DTOComputer;
import model.Company;
import model.Computer;

public class ComputerMapper {

	public Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder(resultSet.getString("computer.name"))
				.id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name")).build())
				.build();
	}

	public static void fromDTO(DTOComputer dtoComputer) {
				
		if(dtoComputer.getName() == "") {
			//return Optional.empty();
		}
//		else {
//			if(dtoComputer.getIntroduced() != "" && dtoComputer.getIntroduced().matches("\\d{4}-\\d{2}-\\d{2}")) {
//				if(dtoComputer.getDiscontinued() != "" && dtoComputer.getDiscontinued().matches("\\d{4}-\\d{2}-\\d{2}")) {
//					if(disconDate.getTime() < introDate.getTime()) {
//						
//						return Optional.empty();
//					}
//					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName())
//							.introduced(new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced())))
//							.discontinued(new Timestamp(DateMapper.getDate(dtoComputer.getDiscontinued())))
//							.build());
//				}
//				else {
//					disconDate = null;
//				}
//			}
//			else {
//				introDate = null;
//				disconDate = null;
//			}
//			
//		}
		
		
	}
}
