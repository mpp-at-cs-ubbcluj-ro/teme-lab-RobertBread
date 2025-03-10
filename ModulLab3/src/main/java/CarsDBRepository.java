import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository {
    private final JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {}", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Masini WHERE manufacturer = ?";
        try (Connection con = dbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, manufacturerN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(new Car(rs.getString("manufacturer"),rs.getString("model"),rs.getInt("year")));
            }
        } catch (SQLException e) {
            logger.error("Error finding cars by manufacturer", e);
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Masini WHERE year BETWEEN ? AND ?";
        try (Connection con = dbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(new Car(rs.getString("manufacturer"),rs.getString("model"),rs.getInt("year")));
            }
        } catch (SQLException e) {
            logger.error("Error finding cars between years", e);
        }
        return cars;
    }

    @Override
    public void add(Car elem) {
        String query = "INSERT INTO Masini(year, model, manufacturer) VALUES (?, ?, ?)";
        try (Connection con = dbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, elem.getYear());
            ps.setString(2, elem.getModel());
            ps.setString(3, elem.getManufacturer());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding car", e);
        }
    }

    @Override
    public void update(Integer id, Car elem) {
        String query = "UPDATE Masini SET year = ?, model = ?, manufacturer = ? WHERE id = ?";
        try (Connection con = dbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, elem.getYear());
            ps.setString(2, elem.getModel());
            ps.setString(3, elem.getManufacturer());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating car", e);
        }
    }

    @Override
    public Iterable<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Masini";
        try (Connection con = dbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(rs.getString("manufacturer"),rs.getString("model"),rs.getInt("year")));
            }
        } catch (SQLException e) {
            logger.error("Error finding all cars", e);
        }
        return cars;
    }
}