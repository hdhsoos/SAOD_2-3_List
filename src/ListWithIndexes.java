import java.util.Scanner;

class ListWithIndexes {
    private int[] array;

    public ListWithIndexes(int MaxSize) {
        array = new int[MaxSize];
        for (int i = 0; i < MaxSize; i++) {
            array[i] = -1;
        }
        array[0] = -1;
    }

    private void printList() {
        if (array[0] == -1) {
            System.out.println("Список пуст.");
        } else {
            int currentIndex = array[0];
            while (currentIndex != -1) {
                System.out.print(array[currentIndex] + " ");
                currentIndex = array[currentIndex + 1];
            }
            System.out.println();
        }
    }

    private int search(int value) {
        int currentIndex = array[0];
        while (currentIndex != -1) {
            if (array[currentIndex] == value) {
                return currentIndex;
            }
            currentIndex = array[currentIndex + 1];
        }
        return -1;
    }

    private void add(int value) {
        int currentIndex = array[0];
        while (array[currentIndex + 1] != -1) {
            currentIndex = array[currentIndex + 1];
        }
        int newIndex = findFreeIndex();
        if (newIndex == -1) {
            System.out.println("Список заполнен.");
            return;
        }
        array[currentIndex + 1] = newIndex;
        array[newIndex] = value;
        array[newIndex + 1] = -1;
    }

    private void addAfter(int valueToAddAfter, int newValue) {
        int indexToAddAfter = search(valueToAddAfter);
        if (indexToAddAfter == -1) {
            System.out.println("Элемент не найден.");
            return;
        }
        int newIndex = findFreeIndex();
        if (newIndex == -1) {
            System.out.println("Список заполнен.");
            return;
        }
        int nextIndex = array[indexToAddAfter + 1];
        array[indexToAddAfter + 1] = newIndex;
        array[newIndex] = newValue;
        array[newIndex + 1] = nextIndex;
    }

    private void addBefore(int valueToAddBefore, int newValue) {
        int indexToAddBefore = search(valueToAddBefore);
        if (indexToAddBefore == -1) {
            System.out.println("Элемент не найден.");
            return;
        }
        int newIndex = findFreeIndex();
        if (newIndex == -1) {
            System.out.println("Список заполнен.");
            return;
        }
        int currentIndex = array[0];
        while (array[currentIndex + 1] != indexToAddBefore) {
            currentIndex = array[currentIndex + 1];
        }
        array[currentIndex + 1] = newIndex;
        array[newIndex] = newValue;
        array[newIndex + 1] = indexToAddBefore;
    }

    private void delete(int valueToRemove) {
        int indexToRemove = search(valueToRemove);
        if (indexToRemove == -1) {
            System.out.println("Элемент не найден.");
            return;
        }
        int currentIndex = array[0];
        while (array[currentIndex + 1] != indexToRemove) {
            currentIndex = array[currentIndex + 1];
        }
        int nextIndex = array[indexToRemove + 1];
        array[currentIndex + 1] = nextIndex;
        array[indexToRemove] = -1;
        array[indexToRemove + 1] = -1;
    }

    private int findFreeIndex() {
        for (int i = 1; i < array.length; i += 2) {
            if (array[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите максимальную длину списка: ");
        int size = scanner.nextInt();
        ListWithIndexes array = new ListWithIndexes(size);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Вывести список на экран.");
            System.out.println("2. Найти элемент в списке.");
            System.out.println("3. Добавить новый элемент в список.");
            System.out.println("4. Добавить новый элемент после заданного.");
            System.out.println("5. Добавить новый элемент перед заданным.");
            System.out.println("6. Удалить заданный элемент.");
            System.out.println("0. Выйти.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    array.printList();
                    break;
                case 2:
                    System.out.println("Введите значение элемента:");
                    int valueToFind = scanner.nextInt();
                    int index = array.search(valueToFind);
                    if (index == -1) {
                        System.out.println("Элемент не найден");
                    } else {
                        System.out.println("Индекс элемента: " + index);
                    }
                    break;
                case 3:
                    System.out.println("Введите значение нового элемента:");
                    int newValue = scanner.nextInt();
                    array.add(newValue);
                    break;
                case 4:
                    System.out.println("Введите значение элемента, после которого нужно добавить новый элемент:");
                    int valueToAddAfter = scanner.nextInt();
                    System.out.println("Введите значение нового элемента:");
                    int newValueToAddAfter = scanner.nextInt();
                    array.addAfter(valueToAddAfter, newValueToAddAfter);
                    break;
                case 5:
                    System.out.println("Введите значение элемента, перед которым нужно добавить новый элемент:");
                    int valueToAddBefore = scanner.nextInt();
                    System.out.println("Введите значение нового элемента:");
                    int newValueToAddBefore = scanner.nextInt();
                    array.addBefore(valueToAddBefore, newValueToAddBefore);
                    break;
                case 6:
                    System.out.println("Введите значение элемента, который нужно удалить:");
                    int valueToRemove = scanner.nextInt();
                    array.delete(valueToRemove);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Ошибка ввода.");
                    break;
            }
        }
    }
}