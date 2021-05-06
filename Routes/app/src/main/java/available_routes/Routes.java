package available_routes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class Routes implements Serializable {
    private HashMap<String, HashMap<String, HashMap<String, List<RoutesDetails>>>> routes = new HashMap<>();
    public static HashMap<String, HashMap<String, List<RoutesDetails>>> from_source = new HashMap<>();
    public static HashMap<String, List<RoutesDetails>> to_destination = new HashMap<>();
    public static List<RoutesDetails> list_routes = new ArrayList<>();
    private static Routes instance = null;
    public static RoutesDetails route;
    private Routes() {

        /* =================================================================================== */
        /* Cento Trans */
        /* Calimanesti - Ramnicu Valcea */
        route = new Bus(2.5f, 3.3f, 19.f, 40.f, 0.f);
        list_routes.add(route);
        to_destination.put("RamnicuValcea", list_routes);
        from_source.put("Calimanesti", to_destination);
        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* Ramnicu Valcea - Bucuresti*/
        route = new Bus(3.3f, 5.55f, 49.f, 154.f, 0.f);
        list_routes.add(route);
        to_destination.put("Bucuresti", list_routes);
        from_source.put("RamnicuValcea", to_destination);
        routes.put("CentoTrans", from_source);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        /* =================================================================================== */

        /* =================================================================================== */
        /* Dacos */
        /* Calimanesti - Ramnicu Valcea*/
        route = new Bus(12.34f, 13.4f, 12.f, 40.f, 4.f);
        list_routes.add(route);
        to_destination.put("RamnicuValcea", list_routes);
        from_source.put("Calimanesti", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* Ramnicu Valcea - Bucuresti*/
        route = new Bus(3.3f, 5.55f, 32.f, 154.f, 18.f);
        list_routes.add(route);
        to_destination.put("Bucuresti", list_routes);
        from_source.put("RamnicuValcea", to_destination);
        routes.put("Dacos", from_source);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        /* =================================================================================== */

        /* =================================================================================== */
        /* Ionescu */
        /* Calimanesti - Ramnicu Valcea*/
        route = new Bus(18.f, 18.2f, 6.f, 17.f, 3.f);
        list_routes.add(route);
        to_destination.put("RamnicuValcea", list_routes);
        from_source.put("Calimanesti", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* Ramnicu Valcea - Bucuresti*/
        route = new Bus(17.05f, 20.08f, 34.f, 177.f, 20.f);
        list_routes.add(route);
        to_destination.put("Bucuresti", list_routes);
        from_source.put("RamnicuValcea", to_destination);
        routes.put("Ionescu", from_source);

        list_routes = new ArrayList<>();
        from_source = new HashMap<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Tren Bucuresti - Constanta - Mangalia
         *  CFR CALATORI
         * */
        /* =================================================================================== */

        /* Bucuresti - Constanta*/
        route = new Train(3.14f, 5.59f, 79.95f, 225.f, 1, 57.f);
        list_routes.add(route);
        route = new Train(3.14f, 5.59f, 54.05f, 225.f, 2, 29.f);
        list_routes.add(route);

        route = new Train(5.52f, 11.04f, 48.5f, 225.f, 1, 33.85f);
        list_routes.add(route);
        route = new Train(5.52f, 11.04f, 30.03f, 225.f, 2, 15.65f);
        list_routes.add(route);

        route = new Train(6.25f, 9.04f, 88.4f, 225.f, 1, 60.55f);
        list_routes.add(route);
        route = new Train(6.25f, 9.04f, 59.6f, 225.f, 2, 31.75f);
        list_routes.add(route);

        route = new Train(7.00f, 9.20f, 88.4f, 225.f, 1, 60.55f);
        list_routes.add(route);
        route = new Train(7.00f, 9.20f, 59.6f, 225.f, 2, 31.75f);
        list_routes.add(route);

        route = new Train(8.15f, 10.35f, 88.4f, 225.f, 1, 60.55f);
        list_routes.add(route);
        route = new Train(8.15f, 10.35f, 59.6f, 225.f, 2, 31.75f);
        list_routes.add(route);
        to_destination.put("Constanta", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("CFRCALATORI", from_source);
        /* =================================================================================== */

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        from_source = new HashMap<>();

        /* =================================================================================== */
        /* Constanta - Mangalia */
        route = new Train(5.59f, 8.05f, 22.1f, 54.f, 1, 16.f);
        list_routes.add(route);
        route = new Train(5.59f, 8.05f, 15.6f, 54.f, 2, 8.f);
        list_routes.add(route);

        route = new Train(9.04f, 10.43f, 22.1f, 54.f, 1, 16.f);
        list_routes.add(route);
        route = new Train(9.04f, 10.43f, 16.9f, 54.f, 2, 8.5f);
        list_routes.add(route);

        route = new Train(9.20f, 11.05f, 24.1f, 54.f, 1, 17.f);
        list_routes.add(route);
        route = new Train(9.20f, 11.05f, 16.9f, 54.f, 2, 8.5f);
        list_routes.add(route);

        route = new Train(10.35f, 12.26f, 24.1f, 54.f, 1, 16.9f);
        list_routes.add(route);
        route = new Train(10.35f, 12.26f, 16.9f, 54.f, 2, 8.5f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        routes.get("CFRCALATORI").put("Constanta", to_destination);
        /* =================================================================================== */

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* =================================================================================== */
        /* AstraTransCarpatic
        *  Bucuresti-Constanta
        * */
        route = new Train(8.0f, 10.22f, 59.5f, 225.f, 2, 29.75f);
        list_routes.add(route);
        to_destination.put("Constanta", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("AstraTransCarpatic", from_source);
        /* =================================================================================== */

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Constanta - Mangalia*/
        route = new Train(10.22f, 11.26f, 17f, 54.f, 2, 8.5f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        routes.get("AstraTransCarpatic").put("Constanta", to_destination);
        /* =================================================================================== */

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        /* SoftTrans
        *  Bucuresti-Constanta
        * */
        /* =================================================================================== */
        route = new Train(10.2f, 12.38f, 55.f, 225.f, 2, 27.5f);
        list_routes.add(route);
        to_destination.put("Constanta", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("SoftTrans", from_source);
        /* =================================================================================== */

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        /* =================================================================================== */
        /* SirTrans
        *  Bucuresti-Mangalia - autobuz
        * */

        route = new Bus(5.3f, 9.3f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(7.f, 11.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(8.3f, 11.30f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(10.f, 14.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(19.f, 23.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("SirTrans", from_source);

        list_routes = new ArrayList<>();
        from_source = new HashMap<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Constanta Mangalia */
        route = new Bus(8.3f, 9.3f, 70.f, 44.f, 35.f);
        list_routes.add(route);
        route = new Bus(10.f, 11.f, 70.f, 44.f, 35.f);
        list_routes.add(route);
        route = new Bus(11.3f, 12.3f, 70.f, 44.f, 35.f);
        list_routes.add(route);
        route = new Bus(13.f, 14.f, 70.f, 44.f, 35.f);
        list_routes.add(route);
        route = new Bus(22.f, 23.f, 70.f, 44.f, 35.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        routes.get("SirTrans").put("Constanta", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Comanto Trans
        *  Bucuresti-Mangalia*/
        route = new Bus(6.15f, 10.1f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(7.45f, 11.4f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(9.15f, 13.1f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(12.f, 16.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(14.3f, 18.3f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(17.f, 21.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(21.15f, 0.25f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("ComantoTrans", from_source);

        list_routes = new ArrayList<>();
        from_source = new HashMap<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        route = new Bus(9.1f, 10.1f, 70.f, 44.f, 30.f);
        list_routes.add(route);
        route = new Bus(10.4f, 11.4f, 70.f, 44.f, 30.f);
        list_routes.add(route);
        route = new Bus(12.1f, 13.1f, 70.f, 44.f, 30.f);
        list_routes.add(route);
        route = new Bus(23.45f, 0.25f, 70.f, 44.f, 30.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        routes.get("ComantoTrans").put("Constanta", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* SimpaTrans
         * Bucuresti-Mangalia
         * */
        route = new Bus(10.45f, 14.25f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(12.15f, 15.55f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(13.45f, 17.25f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(15.15f, 20.f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(16.45f, 20.25f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(18.15f, 21.55f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(19.45f, 23.25f, 70.f, 269.f, 35.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("SimpaTrans", from_source);

        list_routes = new ArrayList<>();
        from_source = new HashMap<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Constanta-Mangalia */
        route = new Bus(13.45f, 14.25f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(15.15f, 15.55f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(16.45f, 17.25f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(19.1f, 20.00f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(19.45f, 20.25f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(21.15f, 21.55f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        route = new Bus(22.45f, 23.25f, 12.f, 44.f, 6.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        routes.get("SimpaTrans").put("Constanta", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Bucuresti-Mangalia
         * */
        route = new Bus(12.00f, 16.00f, 60.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(14.30f, 18.30f, 60.f, 269.f, 35.f);
        list_routes.add(route);
        route = new Bus(17.00f, 21.00f, 60.f, 269.f, 35.f);
        list_routes.add(route);
        to_destination.put("Mangalia", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("GSMTrans", from_source);

        list_routes = new ArrayList<>();
        from_source = new HashMap<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Constanta-Mangalia
         * */
        route = new Bus(14.45f, 15.3f, 14.f, 44.f, 7.f);
        list_routes.add(route);
        route = new Bus(15.f, 16.f, 14.f, 44.f, 7.f);
        list_routes.add(route);
        route = new Bus(17.3f, 18.3f, 14.f, 44.f, 7.f);
        list_routes.add(route);
        route = new Bus(20.f, 21.f, 14.f, 44.f, 7.f);
        list_routes.add(route);

        to_destination.put("Mangalia", list_routes);
        routes.get("GSMTrans").put("Constanta", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Sulina - Tulcea vapor
        * */
        route = new Boat(7.0f,8.3f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(10.0f, 11.3f, 60f, 72.23f, 0.f);
        list_routes.add(route);
        route = new Boat(11.3f, 13.0f, 60f, 72.23f, 0.f);
        list_routes.add(route);
        route = new Boat(14.0f, 15.3f, 60f, 72.23f, 0.f);
        list_routes.add(route);
        route = new Boat(15.30f, 17.0f, 60f, 72.23f, 0.f);
        list_routes.add(route);
        route = new Boat(17.3f, 19.0f, 60f, 72.23f, 0.f);
        list_routes.add(route);
        route = new Boat(19.0f, 20.3f, 60f, 72.23f, 0.f);
        list_routes.add(route);

        to_destination.put("Tulcea", list_routes);
        from_source.put("Sulina", to_destination);
        routes.put("EcoDeltaTrip", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */

        route = new Boat(06.45f,10.0f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(10.0f,13.25f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(12.0f,15.25f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(14.0f,17.25f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(16.0f,19.25f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(18.0f,21.25f,60f,72.23f,0.f);
        list_routes.add(route);
        to_destination.put("Tulcea", list_routes);
        from_source.put("Sulina", to_destination);
        routes.put("TravelDeltaStar", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        route = new Boat(07.0f,08.3f,56f,72.23f,28.f);
        String[] aux = new String[1];
//        aux[0] = "Luni";
//        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Boat(07.0f,10.3f,46f,72.23f,23.f);
        aux = new String[1];
//        aux[0] = "Marti";
//        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Boat(07.0f,08.3f,56f,72.23f,28.f);
        aux = new String[1];
//        aux[0] = "Miercuri";
//        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Boat(07.0f,10.3f,46f,72.23f,23.f);
        aux = new String[1];
//        aux[0] = "Joi";
//        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Boat(07.0f,08.3f,56f,72.23f,28.f);
        aux = new String[1];
//        aux[0] = "Vineri";
//        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Boat(07.0f,10.3f,46f,72.23f,23.f);
        list_routes.add(route);
        aux = new String[1];
//        aux[0] = "Sambata";
//        route.setDaysOfWeek(aux);

        route = new Boat(07.0f,10.3f,46f,72.23f,23.f);
        list_routes.add(route);
        aux = new String[1];
//        aux[0] = "Duminica";
//        route.setDaysOfWeek(aux);

        to_destination.put("Tulcea", list_routes);
        from_source.put("Sulina", to_destination);
        routes.put("NavromDelta", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        route = new Boat(07.0f,08.3f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(10.0f,11.3f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(12.0f,13.3f,60f,72.23f,0.f);
        list_routes.add(route);
        route = new Boat(16.3f,18.0f,60f,72.23f,0.f);
        list_routes.add(route);

        to_destination.put("Tulcea", list_routes);
        from_source.put("Sulina", to_destination);
        routes.put("SulinaTrans", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Tulcea - Bucuresti Tren
        *  CFR CALATORI
        * */
        route = new Train(5.2f, 10.41f, 94.35f, 334.f, 1, 75.5f);
        route.setStudents(40.9f);
        list_routes.add(route);
        route = new Train(5.2f, 10.41f, 63.75f, 334.f, 2, 39.5f);
        route.setStudents(0.f);
        list_routes.add(route);

        route = new Train(15.45f, 20.51f, 94.35f, 334.f, 1, 75.5f);
        route.setStudents(40.9f);
        list_routes.add(route);
        route = new Train(15.45f, 20.51f, 63.75f, 334.f, 2, 39.5f);
        route.setStudents(0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        routes.get("CFRCALATORI").put("Tulcea", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Tulcea Bucuresti autobuz*/
        route = new Bus(3.f, 7.45f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        aux = new String[]{"Luni", "Marti", "Miercuri", "Joi", "Vineri"};
        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Bus(5.f, 9.43f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        aux = new String[]{"Sambata", "Duminica"};
        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Bus(7.f, 11.43f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        aux = new String[]{"Luni", "Marti", "Miercuri", "Joi", "Vineri"};
        route.setDaysOfWeek(aux);
        list_routes.add(route);

        route = new Bus(9.f, 13.43f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        list_routes.add(route);
        route = new Bus(12.f, 16.43f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        list_routes.add(route);
        route = new Bus(15.3f, 20.13f, 90.f, 280.f, 45.f);
        route.setStudents(70.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        from_source.put("Tulcea", to_destination);
        routes.put("AugustinaSRL", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Bucuresti-Madrid
        *  Autobuz
        * */

        route = new Bus(0.0f, 23.45f, 538.66f, 5067.f, 489.96f);
        aux = new String[]{"Marti"};
        route.setDaysOfWeek(aux);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("Amring", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */

        route = new Bus(4.0f, 10.16f, 587.63f, 5137.f, 528.86f);
        aux = new String[]{"Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata"};
        route.setDaysOfWeek(aux);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("TabitaTour", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Bucuresti-Madrid Feroviar*/

        route = new Train(5.1f, 19.55f, 1199.74f, 2474.f, 0, 905.92f);
        list_routes.add(route);
        route = new Train(13.2f, 17.1f, 1199.74f, 2474.f, 0, 905.92f);
        list_routes.add(route);
        routes.get("CFRCALATORI").get("Bucuresti").put("Madrid", list_routes);
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Bucurest-Madrid Aerian*/
        route = new Airplane(5.55f, 9.05f, 260.f, 2472.8f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("WizzAir", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(6.f, 14.1f, 521.f, 3082.09f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("WizzAir + Ryanair", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(8.f, 14.f, 645.f, 3356.46f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("British Airways", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(8.1f, 11.45f, 503.f, 2472.8f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("Tarom", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(12.3f, 19.4f, 640.f, 2798.34f, 1, 0.f);
        routes.get("WizzAir + Ryanair").get("Bucuresti").get("Madrid").add(route);

        route = new Airplane(13.55f, 19.3f, 784.f, 3270.19f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(18.05f, 23.25f, 872.f, 3270.19f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("KLM", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(21.35f, 0.4f, 1108.f, 2472.8f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Madrid", list_routes);
        from_source.put("Bucuresti", to_destination);
        routes.put("Ryanair", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Madrid - Lisabona
        * -Rutier
        * */
        route = new Bus(8.3f, 18.3f, 39.3f, 624.f, 27.51f);
        list_routes.add(route);
        route = new Bus(9.f, 18.3f, 39.3f, 624.f, 27.51f);
        list_routes.add(route);
        route = new Bus(9.3f, 18.3f, 39.3f, 624.f, 27.51f);
        list_routes.add(route);
        route = new Bus(10f, 17.25f, 45f, 624.f, 31.5f);
        list_routes.add(route);
        route = new Bus(10.3f, 17.25f, 45f, 624.f, 31.5f);
        list_routes.add(route);
        route = new Bus(21.3f, 6f, 45f, 624.f, 31.5f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Alsa", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(8.3f, 18.3f, 39.3f, 624.f, 0.f);
        list_routes.add(route);
        route = new Bus(9.f, 18.3f, 39.3f, 624.f, 0.f);
        list_routes.add(route);
        route = new Bus(9.3f, 18.3f, 39.3f, 624.f, 0.f);
        list_routes.add(route);
        route = new Bus(21.3f, 5.3f, 45f, 624.f, 0.f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("EurolinesElvetia", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(10f, 15f, 37.24f, 624.f, 29.f);
        list_routes.add(route);

        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Union Ivkoni International", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Madrid - Lisabona
         * -Aerian
         * */
        route = new Airplane(6.3f, 6.45f, 39.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(14.45f, 15.5f, 76.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(18.5f, 19.10f, 39.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Air Europa", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(7.f, 7.2f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(9.2f, 9.4f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(11.15f, 11.30f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(13.4f, 14.f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(15.35f, 15.55f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(17.55f, 18.15f, 83.94f, 503.27f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("TAP Air Portugal", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(7.25f, 7.5f, 40.f, 503.27f, 0, 36.f);
        list_routes.add(route);
        route = new Airplane(15.4f, 16.05f, 40.f, 503.27f, 0, 36.f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Iberia", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(9.55f, 10.2f, 28.99f, 503.27f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Lisabona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("easyJet", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Lisabona-Sintra tren
        * */
        route = new Train(24.01f, 24.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(24.31f, 01.11f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(01.01f, 01.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(05.41f, 06.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(06.11f, 06.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(06.41f, 07.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(06.56f, 07.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(07.11f, 07.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(07.26f, 08.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(07.41f, 08.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(07.56f, 08.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(08.11f, 08.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(08.26f, 09.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(08.41f, 09.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(08.56f, 09.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(09.11f, 09.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(09.26f, 10.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(09.41f, 10.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.01f, 10.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.21f, 11.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.41f, 11.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.01f, 11.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.21f, 12.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.41f, 12.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.01f, 12.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.21f, 13.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.41f, 13.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.01f, 13.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.21f, 14.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.41f, 14.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.01f, 14.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.21f, 15.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.41f, 15.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.01f, 15.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.21f, 16.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.41f, 16.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.01f, 16.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.21f, 17.05f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.41f, 17.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.56f, 17.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.11f, 17.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.26f, 18.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.41f, 18.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.56f, 18.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.11f, 18.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.26f, 19.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.41f, 19.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.56f, 19.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.11f, 19.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.26f, 20.15f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.41f, 20.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.56f, 20.45f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.11f, 20.51f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.41f, 21.21f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(21.01f, 21.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(21.31f, 22.11f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(22.01f, 22.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(22.31f, 23.11f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(23.01f, 23.41f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(23.31f, 00.11f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        to_destination.put("Sintra", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("CombiosDePortugal", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /*Sintra - Cabo de Roca
        * Autobuz
        * */
        route = new Bus(09.06f, 09.49f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(10.16f, 10.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(11.16f, 11.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(11.46f, 12.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.16f, 12.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.46f, 13.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(13.16f, 13.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(13.46f, 14.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.16f, 14.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.46f, 15.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(15.16f, 15.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(15.46f, 16.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(16.16f, 16.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(16.46f, 17.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(17.16f, 17.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(17.46f, 18.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(18.16f, 18.59f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(18.46f, 19.29f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        to_destination.put("CaboDeRoca", list_routes);
        from_source.put("Sintra", to_destination);
        routes.put("Scotturb", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */

        /* Cascais-cabo de Roca
        * Autobuz*/

        route = new Bus(08.42f, 09.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(09.42f, 10.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(10.12f, 10.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(10.42f, 11.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(11.42f, 12.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.12f, 12.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.42f, 13.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(13.12f, 13.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(13.42f, 14.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.12f, 14.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.42f, 15.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);

        route = new Bus(15.12f, 15.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(15.52f, 16.24f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(16.12f, 16.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(16.42f, 17.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(17.12f, 17.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(17.42f, 18.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(18.12f, 18.44f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(18.42f, 19.14f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);

        to_destination.put("CaboDeRoca", list_routes);
        routes.get("Scotturb").put("Cascais", to_destination);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Lisabona(Cais de Sodre -Cascais
        * Tren
        * */
        route = new Train(24.0f, 24.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(24.3f, 1.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(1.0f, 1.5f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(1.3f, 2.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(5.3f, 6.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(6.0f, 6.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(6.3f, 7.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(7.0f, 7.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(7.12f, 7.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(7.24f, 7.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(7.36f, 8.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(7.48f, 8.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(8.0f, 8.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(8.12f, 8.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(8.24f, 8.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(8.36f, 9.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(8.48f, 9.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(9.0f, 9.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(9.12f, 9.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(9.24f, 9.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(9.36f, 10.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(9.48f, 10.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.0f, 10.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.2f, 11.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(10.4f, 11.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.0f, 11.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.2f, 12.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(11.4f, 12.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.0f, 12.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.2f, 13.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(12.4f, 13.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.0f, 13.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.2f, 14.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.4f, 14.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.0f, 14.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.2f, 15.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.4f, 15.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.0f, 15.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.2f, 16.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.4f, 16.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.0f, 16.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.2f, 17.0f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.4f, 17.2f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.0f, 17.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.12f, 17.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.24f, 17.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.36f, 18.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(17.48f, 18.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.0f, 18.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.12f, 18.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.24f, 18.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.36f, 19.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(18.48f, 19.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.0f, 19.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.12f, 19.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.24f, 19.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.36f, 20.09f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(19.48f, 20.21f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.0f, 20.33f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.12f, 20.45f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.24f, 20.57f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.3f, 21.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(20.5f, 21.3f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(21.1f, 21.5f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(21.3f, 22.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(22.0f, 22.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(22.3f, 23.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(23.0f, 23.4f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(23.3f, 24.1f, 2.25f, 35.f, 0, 1.69f);
        list_routes.add(route);
        to_destination.put("Cascais", list_routes);
        routes.get("CombiosDePortugal").put("Cais Do Sodré", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* =================================================================================== */
        /* Cabo De Roca - Sintra
        * Autobuz
        * */

        route = new Bus(11.44f, 12.32f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.14f, 13.02f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(12.44f, 13.32f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(13.44f, 14.32f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.14f, 15.02f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(14.44f, 15.32f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);
        route = new Bus(15.14f, 16.02f, 1.15f, 20.f, 0.86f);
        list_routes.add(route);

        to_destination.put("Sintra", list_routes);
        routes.get("Scotturb").put("CaboDeRoca", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Sintra - Lisabona
        * Tren
        * */
        route = new Train(12.40f, 13.19f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.00f, 13.39f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.16f, 13.59f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(13.40f, 14.19f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.00f, 14.39f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.16f, 14.59f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(14.40f, 15.19f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.00f, 15.39f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.16f, 15.59f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(15.40f, 16.19f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.00f, 16.39f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);
        route = new Train(16.16f, 16.59f, 2.25f, 30.f, 0, 1.69f);
        list_routes.add(route);

        to_destination.put("Lisabona", list_routes);
        routes.get("CombiosDePortugal").put("Sintra", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();
        /* =================================================================================== */
        /* Lisabona - Paris
        *  Autobuz
        * */

        route = new Bus(6.30f, 9.50f, 64.99f, 1738f, 0.f);
        list_routes.add(route);
        route = new Bus(10.30f, 12.45f, 64.99f, 1738f, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("FlixBus", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(7.00f, 10.45f, 106.f, 1738.f, 0.f);
        list_routes.add(route);
        route = new Bus(13.30f, 15.52f, 106.f, 1738.f, 0.f);
        list_routes.add(route);
        route = new Bus(14.00f, 15.40f, 106.f, 1738.f, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("BlablaBus", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(7.00f, 12.45f, 113.f, 2206f, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("Eurolines Elvetia", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(13.30f, 15.25f, 113.f, 1738f, 79.1f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("Asla", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Lisabona - Bordeaux
         *  autobuz
         * */
        route = new Bus(6.30f, 2.15f, 74.99f, 1165f, 0.f);
        list_routes.add(route);
        route = new Bus(10.30f, 5.05f, 69.99f, 1165f, 0.f);
        list_routes.add(route);
        routes.get("FlixBus").get("Lisabona").put("Bordeaux", list_routes);
        list_routes = new ArrayList<>();

        route = new Bus(7.00f, 2.40f, 98.f, 1208f, 68.6f);
        list_routes.add(route);
        route = new Bus(13.30f, 8.10f, 98.f, 1208f, 68.6f);
        list_routes.add(route);
        routes.get("Asla").get("Lisabona").put("Bordeaux", list_routes);
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Bordeaux - Paris
         *  tren
         * */
        route = new Train(5.45f, 8.08f, 47.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(5.45f, 8.08f, 35.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(6.34f, 8.34f, 90.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(6.34f, 8.34f, 47.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(6.59f, 9.53f, 71.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(6.59f, 9.53f, 59.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(7.04f, 9.08f, 71.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(7.04f, 9.08f, 59.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(8.04f, 10.14f, 65.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(8.04f, 10.14f, 47.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(8.54f, 11.39f, 19.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(9.04f, 11.08f, 65.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(9.04f, 11.08f, 47.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(11.04f, 13.08f, 117.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(11.04f, 13.08f, 59.f, 500.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(12.04f, 14.08f, 77.f, 500.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(12.04f, 14.08f, 59.f, 500.f, 2, 0.f);
        list_routes.add(route);

        to_destination.put("Paris", list_routes);
        from_source.put("Bordeaux", to_destination);
        routes.put("SNFC", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Lisabona - Madrid
        * Autocar
        * */

        route = new Bus(10.30f, 20.30f, 14.99f, 611.f, 0.f);
        list_routes.add(route);
        routes.get("FlixBus").get("Lisabona").put("Madrid", list_routes);

        route = new Bus(21.30f, 7.15f, 18.f, 611.f, 12.6f);
        list_routes.add(route);
        routes.get("Asla").get("Lisabona").put("Madrid", list_routes);

        route = new Bus(21.30f, 7.15f, 50.f, 611.f, 12.6f);
        list_routes.add(route);
        routes.get("Eurolines Elvetia").get("Lisabona").put("Madrid", list_routes);

        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Madrid - Paris
         * Tren
         * */
        route = new Train(6.30f, 16.46f, 326.7f, 1053.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(6.30f, 16.46f, 271f, 1053.f, 2, 0.f);
        list_routes.add(route);

        route = new Train(10.30f, 6.52f, 271.7f, 1053.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(10.30f, 6.52f, 216f, 1053.f, 2, 0.f);
        list_routes.add(route);

        to_destination.put("Paris", list_routes);
        routes.get("SNFC").put("Madrid", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Madrid - Barcelona
         * Autocar
         * */
        route = new Bus(8.00f, 15.50f, 33.48f, 616.f, 23.44f);
        list_routes.add(route);
        route = new Bus(8.10f, 15.50f, 33.48f, 616.f, 23.44f);
        list_routes.add(route);
        to_destination.put("Barcelona", list_routes);
        routes.get("Asla").put("Madrid", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Bus(11.15f, 18.35f, 33.48f, 616.f, 0f);
        list_routes.add(route);
        to_destination.put("Barcelona", list_routes);
        routes.get("Eurolines Elvetia").put("Madrid", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Madrid - Barcelona
         * Avion
         * */
        route = new Airplane(7.30f, 8.55f, 53.f, 504.48f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(20.30f, 21.45f, 40.f, 504.48f, 0, 0.f);
        list_routes.add(route);

        to_destination.put("Barcelona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Air Europa", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(9.05f, 10.25f, 68.f, 504.48f, 0, 0.f);
        list_routes.add(route);

        to_destination.put("Barcelona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("Vueling", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();
        /* =================================================================================== */
        /* Madrid-Barcelona
        *  tren
        *  */

        route = new Train(5.51f, 9.10f, 66.65f, 504.48f, 1, 0.f);
        list_routes.add(route);
        route = new Train(5.51f, 9.10f, 64.25f, 504.48f, 2, 0.f);
        list_routes.add(route);

        route = new Train(8.15f, 14.29f, 61.4f, 504.48f, 1, 0.f);
        list_routes.add(route);
        route = new Train(8.15f, 14.29f, 61.4f, 504.48f, 2, 0.f);
        list_routes.add(route);

        route = new Train(9.03f, 18.07f, 34.60f, 504.48f, 2, 0.f);
        list_routes.add(route);

        to_destination.put("Barcelona", list_routes);
        from_source.put("Madrid", to_destination);
        routes.put("RENFE", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Barcelona-Paris
         *  tren
         *  */
        route = new Bus(8.50f, 5.30f, 43.98f, 804.f, 0.f);
        list_routes.add(route);
        route = new Bus(19.50f, 10.25f, 19.99f, 804.f, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        routes.get("FlixBus").put("Barcelona", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Barcelona-Paris
         *  Avion
         *  */
        route = new Airplane(6.25f, 8.20f, 27.f, 803.78f, 0, 0.f);
        list_routes.add(route);
        route = new Airplane(19.10f, 21.05f, 23.f, 803.78f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        routes.get("Ryanair").put("Barcelona", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(13.10f, 15.05f, 69.f, 803.78f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        routes.get("easyJet").put("Barcelona", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        /* =================================================================================== */
        /* Barcelona-Paris
         *  tren
         *  */
        route = new Train(10.05f, 16.46f, 203.20f, 804.f, 1, 0.f);
        list_routes.add(route);
        route = new Train(10.05f, 16.46f, 173.20f, 804.f, 2, 0.f);
        list_routes.add(route);

        to_destination.put("Paris", list_routes);
        routes.get("SNFC").put("Barcelona", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        /* =================================================================================== */
        /* Lisabona - Paris
         *  Avion
         * */
        route = new Airplane(6.30f, 10.00f, 129.f, 1454.54f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        routes.get("Ryanair").put("Lisabona", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        route = new Airplane(7.00f, 10.30f, 133.f, 1454.54f, 0, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        routes.get("easyJet").put("Lisabona", to_destination);

        list_routes = new ArrayList<>();
        to_destination = new HashMap<>();

        route = new Airplane(10.00f, 9.10f, 55.f, 1488.82f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(18.00f, 22.55f, 67.f, 1488.82f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(23.20f, 9.10f, 53.f, 1488.82f, 1, 0.f);
        list_routes.add(route);

        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("TAP Portugal", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(22.20f, 17.55f, 76.f, 3861.35f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Paris", list_routes);
        from_source.put("Lisabona", to_destination);
        routes.put("Air Malita", from_source);
        /* =================================================================================== */
        /* Paris - Bucuresti
        * Avion
        * */
        route = new Airplane(11.40f, 13.05f, 165.82f, 4555.51f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(12.40f, 14.05f, 165.82f, 4555.51f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(17.50f, 14.05f, 165.82f, 4555.51f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(18.35f, 14.05f, 165.82f, 4555.51f, 1, 0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("AirFrance", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(10.45f, 1.20f, 286.f, 4466.f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("LOT", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(19.35f, 14.10f, 134.f, 3378.31f, 1, 0.f);
        list_routes.add(route);
        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("SAS + LOT", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(20.55f, 23.15f, 147.67f, 4037.86f, 1, 0.f);
        list_routes.add(route);
        route = new Airplane(18.35f, 13.10f, 141f, 5216.89f, 2, 0.f);
        list_routes.add(route);
        route = new Airplane(11.40f, 13.10f, 141f, 5216.89f, 2, 0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        routes.get("KLM").put("Paris", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(7.40f, 17.20f, 416.f, 5172.4f, 3, 0.f);
        list_routes.add(route);
        route = new Airplane(10.50f, 17.35f, 416.f, 5172.4f, 3, 0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("Finnair + LOT", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(10.50f, 17.35f, 444.86f, 7547.8f, 3, 0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("Airfrance + Finnair", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        route = new Airplane(7.40f, 13.10f, 208.8f, 5193.74f, 3, 0.f);
        list_routes.add(route);

        to_destination.put("Bucuresti", list_routes);
        from_source.put("Paris", to_destination);
        routes.put("Finnair + KLM", from_source);

        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();



        /* =================================================================================== */
        /* Vadso Nuorgam
        *  Nuorgam Vadso
        *  Autobuz
        * */
        /* Vadsø – Nuorgam */
        route = new Bus(7.26f, 9.31f, 19f, 144.f, 14.f);
        list_routes.add(route);
        route = new Bus(12.46f, 14.471f, 19f, 144.f, 14.f);
        list_routes.add(route);
        to_destination.put("Nuorgam", list_routes);
        from_source.put("Vadsø", to_destination);
        routes.put("Snelandia", from_source);
        to_destination = new HashMap<>();
        from_source = new HashMap<>();
        list_routes = new ArrayList<>();

        /* Nuorgam - Vadsø */
        route = new Bus(17.04f, 18.55f, 19f, 144.f, 14.f);
        list_routes.add(route);
        to_destination.put("Vadsø", list_routes);
        routes.get("Snelandia").put("Nuorgam", to_destination);

        to_destination = new HashMap<>();
        list_routes = new ArrayList<>();

    }

    public static Routes getInstance() {
        if (instance == null) {
            instance = new Routes();
        }
        return instance;
    }

    public HashMap<String, HashMap<String, HashMap<String, List<RoutesDetails>>>> getRoutes() {
        return this.routes;
    }
}
