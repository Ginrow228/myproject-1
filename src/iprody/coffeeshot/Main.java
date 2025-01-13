package iprody.coffeeshot;

public class Main {
    public static void main(String[] args){
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        board.add("Алиса");
        board.add("Дмитрий");
        board.add("Сергей");
        board.add("Александр");
        board.add("Матвей");
        board.add("Ирина");
        board.add("Мария");
        board.add("Петр");
        board.add("Анна");
        board.draw();

        board.deliver();
        board.deliver();
        board.draw();

        try {
            board.deliver(10);
        } catch (OrderNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

        board.add("Иван");
        board.add("Кирилл");
        board.add("Ангелина");
        board.add("Эмиль");
        board.draw();

        try {
            board.deliver(10);
        } catch (OrderNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

        board.draw();



    }
}
