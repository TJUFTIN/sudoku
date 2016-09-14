package sudoku;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IEUser on 18.06.2016.
 */
public class Sudoku { //test
    static boolean duplicates = false;
    static ArrayList<TableTree> tableTreeList = new ArrayList<>();

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        int versionOfTableInitialization = 3; //23 (1) ok, 25 (4) ok, 27 (5) ok, 30 (6) ok, 17 (7), (2) ok, (3) ok
        int iterationNumber = 0;

        Var[][] table = new Var[9][9];

        tableTreeList.add(new TableTree(table, iterationNumber));

        initializeTable(table, versionOfTableInitialization);

        ArrayList<ArrayList<Var>> groups = new ArrayList<>();
        ArrayList<ArrayList<Var>> checks = new ArrayList<>();

        ArrayList<Var> arrayGr1 = new ArrayList();
        groups.add(arrayGr1);
        ArrayList<Var> arrayGr2 = new ArrayList();
        groups.add(arrayGr2);
        ArrayList<Var> arrayGr3 = new ArrayList();
        groups.add(arrayGr3);
        ArrayList<Var> arrayGr4 = new ArrayList();
        groups.add(arrayGr4);
        ArrayList<Var> arrayGr5 = new ArrayList();
        groups.add(arrayGr5);
        ArrayList<Var> arrayGr6 = new ArrayList();
        groups.add(arrayGr6);
        ArrayList<Var> arrayGr7 = new ArrayList();
        groups.add(arrayGr7);
        ArrayList<Var> arrayGr8 = new ArrayList();
        groups.add(arrayGr8);
        ArrayList<Var> arrayGr9 = new ArrayList();
        groups.add(arrayGr9);

        ArrayList<Var> arrayCh1 = new ArrayList();
        checks.add(arrayCh1);
        ArrayList<Var> arrayCh2 = new ArrayList();
        checks.add(arrayCh2);
        ArrayList<Var> arrayCh3 = new ArrayList();
        checks.add(arrayCh3);
        ArrayList<Var> arrayCh4 = new ArrayList();
        checks.add(arrayCh4);
        ArrayList<Var> arrayCh5 = new ArrayList();
        checks.add(arrayCh5);
        ArrayList<Var> arrayCh6 = new ArrayList();
        checks.add(arrayCh6);
        ArrayList<Var> arrayCh7 = new ArrayList();
        checks.add(arrayCh7);
        ArrayList<Var> arrayCh8 = new ArrayList();
        checks.add(arrayCh8);
        ArrayList<Var> arrayCh9 = new ArrayList();
        checks.add(arrayCh9);

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].group.equals("group1")) {
                    arrayGr1.add(table[i][j]);
                } else if (table[i][j].group.equals("group2")) {
                    arrayGr2.add(table[i][j]);
                } else if (table[i][j].group.equals("group3")) {
                    arrayGr3.add(table[i][j]);
                } else if (table[i][j].group.equals("group4")) {
                    arrayGr4.add(table[i][j]);
                } else if (table[i][j].group.equals("group5")) {
                    arrayGr5.add(table[i][j]);
                } else if (table[i][j].group.equals("group6")) {
                    arrayGr6.add(table[i][j]);
                } else if (table[i][j].group.equals("group7")) {
                    arrayGr7.add(table[i][j]);
                } else if (table[i][j].group.equals("group8")) {
                    arrayGr8.add(table[i][j]);
                } else if (table[i][j].group.equals("group9")) {
                    arrayGr9.add(table[i][j]);
                }
                if (table[i][j].check != null) {
                    if (table[i][j].check.equals("check1")) {
                        arrayCh1.add(table[i][j]);
                    } else if (table[i][j].check.equals("check2")) {
                        arrayCh2.add(table[i][j]);
                    } else if (table[i][j].check.equals("check3")) {
                        arrayCh3.add(table[i][j]);
                    } else if (table[i][j].check.equals("check4")) {
                        arrayCh4.add(table[i][j]);
                    }else if (table[i][j].check.equals("check5")) {
                        arrayCh5.add(table[i][j]);
                    }else if (table[i][j].check.equals("check6")) {
                        arrayCh6.add(table[i][j]);
                    }else if (table[i][j].check.equals("check7")) {
                        arrayCh7.add(table[i][j]);
                    }else if (table[i][j].check.equals("check8")) {
                        arrayCh8.add(table[i][j]);
                    }else if (table[i][j].check.equals("check9")) {
                        arrayCh9.add(table[i][j]);
                    }
                }
                if (table[i][j].values == null) {
                    table[i][j].values = "123456789";
                }
                table[i][j].x = i;
                table[i][j].y = j;
            }
        }
        /*
        Если решение нода - false, то находим ячейку по которой будем строить дерево от нода.
        Строим дерево. рекурсионно вызываем метод по решению дерева

        Не учитывается возможность сравнения хода решения по детям нода для выявления общих закономерностей.
        Дальнейшее сравнение приведет к рекурсионному вызову метода по перебору детей одного нода для поиска общщих закономерностей.
        Поиск решения пока возможен только для 1-ого уровня ниже нода, от которого будет строиться дерево.
        Дальнейший поиск закономерностей будет относиться к нодам детей и общая закономерность будет выявляться на их уровнях.


        Имеется ИД родительского нода.
        По кол-ву значений в ячейке определяется кол-во нодов детей.
        для каждого из новых нодов запускается проверка.
         */

        if (resolveSudoku(tableTreeList.get(iterationNumber).table, groups, checks, iterationNumber)){
            resolveSudokuRecursion(iterationNumber,groups, checks);
        }

        for (int i = 0; i < tableTreeList.size(); i++)
        {
            printTable(tableTreeList.get(i).table,"all");
            System.out.println();
            System.out.println("iterationNumber = " + tableTreeList.get(i).iterationNumber
                    + " parentIterationNumber = " + tableTreeList.get(i).parentIterationNumber);
            System.out.println("status = " + tableTreeList.get(i).status);
        }

        System.out.println("Solutions:");
        for (int i = 0; i < tableTreeList.size(); i++)
        {
            if (tableTreeList.get(i).status == 2) {
                printTable(tableTreeList.get(i).table,"all");
                System.out.println();
                System.out.println("iterationNumber = " + tableTreeList.get(i).iterationNumber
                        + " parentIterationNumber = " + tableTreeList.get(i).parentIterationNumber);
            }
        }
        System.out.println("End!");


        for (int i = 0; i < tableTreeList.size(); i++)
        {
            System.out.println("iterationNumber = " + tableTreeList.get(i).iterationNumber
                    + " parentIterationNumber = " + tableTreeList.get(i).parentIterationNumber
                    + " status = " + tableTreeList.get(i).status
                    + " x = " + tableTreeList.get(i).cellX
                    + " y = " + tableTreeList.get(i).cellY
                    + " valueToTry = " + tableTreeList.get(i).valueToTry
                    + " children " + tableTreeList.get(i).childrenIterationList);
        }
        System.out.println("Stard date " + d);
        System.out.println("End date " + new Date());
    }
    /*
    Рекурсивный метод постройки и проверки дерева проверки
     */
    private static void resolveSudokuRecursion (int iterationNumber, ArrayList<ArrayList<Var>> groups,
                                                ArrayList<ArrayList<Var>> checks) {
        TableTree parentTableNod = getTableNode(iterationNumber);
        int parentIterationNumber = iterationNumber;
        int valueToTryLenght = 0;
        String valueToTry = new String();
        boolean stop = false;

        int x = -1;
        int y = -1;
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                if (parentTableNod.table[i][j].values.length() > 1) {
                    valueToTry = parentTableNod.table[i][j].values;
                    valueToTryLenght = valueToTry.length();
                    x = i;
                    y = j;
                    stop = true;
                    break;
                }
                if (i == 8 && j == 8) {
                    System.out.println("Nothing to try!");
                }
            }
            if (stop) break;
        }
        System.out.println("x = " + x + " y = " + y + " valueToTryLenght " + valueToTryLenght + " value " + valueToTry);

        for (int i = 0; i < valueToTryLenght; i++) {
            iterationNumber = getNewIterationNumber(iterationNumber);
            tableTreeList.add(new TableTree(parentTableNod.table, iterationNumber, parentIterationNumber, x, y, valueToTry));
            //parentTableNod.childrenIterationList.add(iterationNumber);
            getTableNode(parentIterationNumber).childrenIterationList.add(new Integer(iterationNumber));
            TableTree newTableTreeNod = getTableNode(iterationNumber);
            newTableTreeNod.table[x][y].values = String.valueOf(getTableNode(parentIterationNumber).table[x][y].values.charAt(i));

            duplicates = false;
            resolveSudoku(newTableTreeNod.table, groups, checks, iterationNumber);

            if (newTableTreeNod.status == 0) {
                resolveSudokuRecursion (iterationNumber,groups, checks);
            }
            //printTable(getTableNode(iterationNumber).table, "all");
        }
    }

    /*
    Метод по запуску проверки
     */
    private static boolean resolveSudoku (Var[][] tab, ArrayList<ArrayList<Var>> groups, ArrayList<ArrayList<Var>> checks,
                                          int iterationNumber){
        printTable(tab, "all");
        System.out.println();

        int iteration = 0;
        LinkedList<Rule> listRules;
        boolean needTryAgain = false;
        byte status;

        while (!duplicates) {
            duplicates = true;
            iteration++;
            listRules = new LinkedList<>();

            for (ArrayList<Var> group : groups) {
                removeDuplicatesArray(tab, group);
            }
            for (ArrayList<Var> check : checks) {
                removeDuplicatesArray(tab, check);
            }
            removeDuplicatesValuesFromTable(tab);

            for (ArrayList<Var> group : groups) {
                findRuleForGroup(group, listRules);
            }
            for (ArrayList<Var> check : checks) {
                findRuleForCheck(check, listRules);
            }

            findRuleForGroupByLine(tab, listRules);
            findRuleForCheckByLine(tab, listRules);

            removeDuplicatesFromTableByRuleInGroups(tab, listRules);
            removeDuplicatesFromTableByRuleInLines(tab, listRules);

            for (ArrayList<Var> group : groups) {
                FindUniqueInGroupOrCheckAndReplace(tab, group);
            }
            for (ArrayList<Var> check : checks) {
                FindUniqueInGroupOrCheckAndReplace(tab, check);
            }

            printTable(tab, "all");
            System.out.println();

            for (ArrayList<Var> group : groups) {
                CheckUniqueInGroupOrCheck(group);
            }
            for (ArrayList<Var> check : checks) {
                CheckUniqueInGroupOrCheck(check);
            }
            duplicates = CheckUniqueInLine(tab, duplicates);
            System.out.println("iteration = " + iteration + " with iterationNumber = " + iterationNumber);
        }

        status = CheckAllCells(tab);
        if (status == 0) {
            needTryAgain = true;
        } else if (status == 1 || status == 2) {
            needTryAgain = false;
        }
        System.out.println("iterationNumber = " + iterationNumber + " completed");
        getTableNode(iterationNumber).setStatus(status);

        return needTryAgain;
    }

    /*
    Удаление значений из таблицы по правилу № 1 (искали уникальность значения в группе по линиям)
     */
    private static void removeDuplicatesFromTableByRuleInLines(Var[][] tab, List<Rule> list) {
        /*
        Берем правило, удаляем значение по линиям
         */
        for (Rule rule : list) {
            for (int i = 0; i < 9 ; i++){
                for (int j = 0; j < 9 ; j++) {
                    if (!rule.line) {
                        if (rule.group != null) {
                            if (!tab[i][j].group.equals(rule.group) && rule.lineNumber == j
                                    && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                        rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                            }
                        } else {
                            if (tab[i][j].check != null) {
                                if (!tab[i][j].check.equals(rule.check) && rule.lineNumber == j
                                        && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                    changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                            rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                                }
                            } else {
                                if (rule.lineNumber == j && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                    changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                            rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                                }
                            }
                        }
                    } else {
                        if (rule.group != null) {
                            if (!tab[i][j].group.equals(rule.group) && rule.lineNumber == i
                                    && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                        rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                            }
                        } else {
                            if (tab[i][j].check != null) {
                                if (!tab[i][j].check.equals(rule.check) && rule.lineNumber == i
                                        && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                    changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                            rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                                }
                            } else {
                                if (rule.lineNumber == i && tab[i][j].values.contains(Integer.toString(rule.value))) {
                                    changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInLines",
                                            rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    Для удаления занчений по правилу в группе
     */
    private static void removeDuplicatesFromTableByRuleInGroups(Var[][] tab, List<Rule> list) {
            for (Rule rule : list) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (rule.group != null && tab[i][j].group != null && tab[i][j].group.equals(rule.group)) {
                        if (rule.line && tab[i][j].values.contains(Integer.toString(rule.value)) && i != rule.lineNumber) {
                            changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInGroups",
                                    rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                        } else if (!rule.line && tab[i][j].values.contains(Integer.toString(rule.value)) && j != rule.lineNumber) {
                            changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInGroups",
                                    rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                        }
                    } else if (rule.check != null && tab[i][j].check != null && tab[i][j].check.equals(rule.check)) {
                        if (rule.line && tab[i][j].values.contains(Integer.toString(rule.value)) && i != rule.lineNumber) {
                            changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInGroups",
                                    rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                        } else if (!rule.line && tab[i][j].values.contains(Integer.toString(rule.value)) && j != rule.lineNumber) {
                            changeCellValue(tab, i, j, Integer.toString(rule.value), "removeDuplicatesFromTableByRuleInGroups",
                                    rule.group, Integer.toString(rule.value), rule.line, rule.lineNumber);
                        }
                    }
                }
            }
        }
    }

    /*
    Удаление ненужных значений из таблицы по группам (если значение уникально, то удаляем его по линиям)
     */
    private static void removeDuplicatesArray (Var[][] tab, List<Var> groupOrCheck) {
        for (Var a : groupOrCheck) {
            if (a.values.length() == 1) {
                for (Var b : groupOrCheck) {
                    if ( b.values.contains(a.values) && b.values.length() > 1 /*&& a.x != b.x && a.y != b.y*/) {
                        changeCellValue(tab, b.x, b.y, a.values, "removeDuplicatesArray");
                    }
                }
            }
        }
    }

    /*
    Удаление ненужных значений из таблицы по строкам и столбцам
     */
    private static void removeDuplicatesValuesFromTable(Var[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].values.length() == 1) {
                    for (int w = 0; w < table[0].length; w++) {
                        if ( table[i][w].values.contains(table[i][j].values) && table[i][w].values.length() > 1) {
                            changeCellValue(table, i, w, table[i][j].values, "removeDuplicatesValuesFromTable");
                        }
                        if ( table[w][j].values.contains(table[i][j].values) && table[w][j].values.length() > 1) {
                            changeCellValue(table, w, j, table[i][j].values, "removeDuplicatesValuesFromTable");
                        }
                    }
                }
            }
        }
    }

    /*
    Замена значения в таблице
     */
    private static void changeCellValue (Var[][] table, int i, int j, String value, String methodName) {
        String valueBefore = table[i][j].values;
        for (int k = 0; k < value.length(); k++) {
            table[i][j].values = table[i][j].values.replaceAll(Character.toString(value.charAt(k)), "");
        }
        printChanges(i, j, valueBefore, table[i][j].values, value, methodName);
        duplicates = false;
    }

    private static void changeCellValue (Var[][] table, int i, int j, String value, String methodName, String groupNameInRule,
                                         String ruleValue, boolean lineTrueColumnFalse, int lineNumber) {
        String valueBefore = table[i][j].values;
        for (int k = 0; k < value.length(); k++) {
            table[i][j].values = table[i][j].values.replaceAll(Character.toString(value.charAt(k)), "");
        }
        printChanges(i, j, valueBefore, table[i][j].values, value, methodName, groupNameInRule,
                ruleValue, lineTrueColumnFalse, lineNumber);
        duplicates = false;
    }
    /*
    Логирование изменения значения
     */
    private static void printChanges (int i, int j, String valueBefore, String valueAfter, String value, String methodName) {
        System.out.println("[" + (i + 1) + "][" + (j + 1) + "] from " + valueBefore + " to " + valueAfter + " value " + value + " method " + methodName);
    }

    private static void printChanges (int i, int j, String valueBefore, String valueAfter, String value, String methodName,
                                      String groupNameInRule, String ruleValue, boolean lineTrueColumnFalse, int lineNumber) {
        String str;
        if (lineTrueColumnFalse) str = "line"; else str = "column";
        System.out.println("[" + (i + 1) + "][" + (j + 1) + "] from " + valueBefore + " to " + valueAfter + " value " + value + " method " + methodName +
        " By_rule: " + groupNameInRule + " value " + ruleValue + " " + str + " " + (lineNumber + 1));
    }
    /*
    Создание правила для группы №1, ищем уникальность значения в группе по линиям
     */
    private static void findRuleForGroup (List<Var> group, List<Rule> list) {
        for (int i = 1; i < 10; i++) {
            LinkedList<Integer> lines = new LinkedList<>();
            LinkedList<Integer> columns = new LinkedList<>();
            for (Var a : group) {
                if (a.values.contains(Integer.toString(i))) {
                    if (!lines.contains(a.x)) {
                        lines.add(a.x);
                    }
                    if (!columns.contains(a.y)) {
                            columns.add(a.y);
                    }
                }
            }

            if (lines.size() == 1) {
                System.out.println(group.get(0).group + " value " + i + " line " + (lines.getFirst() + 1) + " findRuleForGroup");
                list.add(new Rule(group.get(0).group, null, true, i, lines.getFirst()));
            }
            if (columns.size() == 1) {
                System.out.println(group.get(0).group + " value " + i + " column " + (columns.getFirst() + 1) + " findRuleForGroup");
                list.add(new Rule(group.get(0).group, null, false, i, columns.getFirst()));
            }
        }
    }

    /*
    Создание правила для проверки №1, ищем уникальность значения в проверке по линиям
     */
    private static void findRuleForCheck (List<Var> array, List<Rule> list) {
        for (int i = 1; i < 10; i++) {
            LinkedList<Integer> lines = new LinkedList<>();
            LinkedList<Integer> columns = new LinkedList<>();
            for (Var a : array) {
                    if (a.values.contains(Integer.toString(i))) {
                        if (!lines.contains(a.x)) {
                            lines.add(a.x);
                        }
                        if (!columns.contains(a.y)) {
                            columns.add(a.y);
                        }
                    }
            }

            if (lines.size() == 1) {
                System.out.println(array.get(0).check + " value " + i + " line " + (lines.getFirst() + 1) + " findRuleForCheck");
                list.add(new Rule(null, array.get(0).check, true, i, lines.getFirst()));
            }
            if (columns.size() == 1) {
                System.out.println(array.get(0).check + " value " + i + " column " + (columns.getFirst() + 1) + " findRuleForCheck");
                list.add(new Rule(null, array.get(0).check, false, i, columns.getFirst()));
            }
        }
    }

    /*
    Создание правила № 2 по строке, ищем по линии кол-во вхождений значения в группы
     */
    private static void findRuleForGroupByLine (Var[][] tab, List<Rule> list) {
        for (int k = 1; k < 10; k++) {
            for (int i = 0; i < 9; i++) {
                ArrayList<String> lines = new ArrayList<>();
                ArrayList<String> columns = new ArrayList<>();

                for (int j = 0; j < 9; j++) {
                    if (tab[i][j].values.contains(Integer.toString(k))) {
                        if (!columns.contains(tab[i][j].group)) {
                            columns.add(tab[i][j].group);
                        }
                    }
                    if (tab[j][i].values.contains(Integer.toString(k))) {
                        if (!lines.contains(tab[j][i].group)) {
                            lines.add(tab[j][i].group);
                        }
                    }
                }

                if (lines.size() == 1) {
                    System.out.println(lines.get(0) + " value " + k + " column " + (i + 1) + " findRuleForGroupByLine");
                    list.add(new Rule(lines.get(0), null, false, k, i));
                }
                if (columns.size() == 1) {
                    System.out.println(columns.get(0) + " value " + k + " line " + (i + 1) + " findRuleForGroupByLine");
                    list.add(new Rule(columns.get(0), null, true, k, i));
                }
            }
        }
    }

    /*
     Создание правила № 2 по строке, ищем по линии кол-во вхождений значения в группы и проверки
    */
    private static void findRuleForCheckByLine (Var[][] tab, List<Rule> list) {
        for (int k = 1; k < 10; k++) {
            for (int i = 0; i < 9; i++) {
                ArrayList<String> lines = new ArrayList<>();
                ArrayList<String> columns = new ArrayList<>();

                for (int j = 0; j < 9; j++) {
                    if (tab[i][j].values.contains(Integer.toString(k))) {
                        if (tab[i][j].check == null && !columns.contains(tab[i][j].group)) {
                            columns.add(tab[i][j].group);
                        }
                        if (tab[i][j].check != null && !columns.contains(tab[i][j].check)) {
                            columns.add(tab[i][j].check);
                        }
                    }
                    if (tab[j][i].values.contains(Integer.toString(k))) {
                        if (tab[j][i].check == null && !lines.contains(tab[j][i].group)) {
                            lines.add(tab[j][i].group);
                        }
                        if (tab[j][i].check != null && !lines.contains(tab[i][j].check)) {
                            lines.add(tab[j][i].check);
                        }
                    }
                }

                if (lines.size() == 1 && lines.get(0).contains("check")) {
                    System.out.println(lines.get(0) + " value " + k + " column " + (i + 1) + " findRuleForCheckByLine");
                    list.add(new Rule(null, lines.get(0), false, k, i));
                }
                if (columns.size() == 1 && columns.get(0).contains("check")) {
                    System.out.println(columns.get(0) + " value " + k + " line " + (i + 1) + " findRuleForCheckByLine");
                    list.add(new Rule(null, columns.get(0), true, k, i));
                }
            }
        }
    }
    /*
    Поиск уникальных значений внутри групп и проверок
     */
    private static void FindUniqueInGroupOrCheckAndReplace(Var[][] tab, List<Var> groupOrCheck) {
        int countValues;
        int x = 9;
        int y = 9;
        for (int i = 1; i < 10; i++) {
            countValues = 0;
            for (Var a : groupOrCheck) {
                if (a.values.contains(Integer.toString(i))) {
                    countValues++;
                    x = a.x;
                    y = a.y;
                }
            }
            if (countValues == 1 && tab[x][y].values.length() > 1) {
                String newReplaceValue = tab[x][y].values.replaceAll(Integer.toString(i), "");
                changeCellValue (tab, x, y, newReplaceValue, "FindUniqueInGroupOrCheckAndReplace");
            }
        }
    }

    /*
    Проверка по группе, что уникальные значения не повторяются
     */
    private static void CheckUniqueInGroupOrCheck(List<Var> groupOrCheck) {
        for (int value = 1; value < 10; value++) {
            int countValueInGroup = 0;
            for (Var cell : groupOrCheck) {
                if (cell.values.equals(Integer.toString(value))) {
                    countValueInGroup++;
                }
            }
            if (countValueInGroup > 1) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }

    /*
    Проверка по линии, что уникальные значения не повторяются
     */
    private static boolean CheckUniqueInLine(Var[][] tab, boolean duplicates) {
            for (int x = 0 ; x < 9 ; x++) {
                for (int value = 1; value < 10; value++) {
                    int countLines = 0;
                    int countColumns = 0;
                    for (int y = 0; y < 9; y++) {
                        if (tab[x][y].values.equals(Integer.toString(value))) {
                            countColumns++;
                        }
                        if (tab[y][x].values.equals(Integer.toString(value))) {
                            countLines++;
                        }
                    }
                    if (countColumns > 1 || countLines > 1) {
                        System.out.println("-------------------------------------------------------");
                        return true; // !!
                    }
                }
            }
        return duplicates;
    }

    /*
    Последняя повторная проверка что все Ок
     */
    private static byte CheckAllCells(Var[][] tab) {
        byte status; // 0 - needToTryAgain 1 - error 2 - ok
        boolean clean = true;
        for (int x = 0 ; x < 9 ; x++) {
            for (int value = 1; value < 10; value++) {
                int countLines = 0;
                int countColumns = 0;
                for (int y = 0; y < 9; y++) {
                    if (tab[x][y].values.equals(Integer.toString(value))) {
                        countColumns++;
                    }
                    if (tab[y][x].values.equals(Integer.toString(value))) {
                        countLines++;
                    }
                    if (tab[x][y].values.length() > 1) clean = false;
                    if (tab[x][y].values.length() < 1) {
                        System.out.println("Error!!!");
                        status = 1;
                        return status;
                    }
                }
                if (countColumns > 1 || countLines > 1) {
                    System.out.println("Error!!!");
                    status = 1;
                    return status;
                }
            }
        }
        if (clean) {
            System.out.println("Clean!");
            status = 2;
        } else {
            System.out.println("Not clean...");
            status = 0;
        }
        return status;
    }

    /*
    Печать таблицы
     */
    private static void printTable (Var[][] table, String a) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (a == "all") {
                    System.out.print(table[i][j].values);
                    for (int k = 0; k < 9-table[i][j].values.length(); k++)
                    {
                        System.out.print(" ");
                    }
                    if (j < 8) {
                        if (!table[i][j].group.equals(table[i][j+1].group)) {
                            System.out.print(" | ");
                        } else {
                            System.out.print("   ");
                        }
                    }

                } else {
                    if (table[i][j].values.length() == 1) {
                        System.out.print(table[i][j].values+ " ");
                    } else {
                        System.out.print("_ ");
                    }
                }
            }

            if (i < 8) {
                System.out.println();
            }

            for (int j = 0; j < table[0].length; j++)            {
                if (a == "all") {
                    if (i < 8) {
                        if (!table[i][j].group.equals(table[i+1][j].group)) {         // a!=c
                            if (j != 0) {
                                System.out.print("-"); // - add
                            }
                            System.out.print("----------");
                            if (j < 8) {
                                if (!table[i][j].group.equals(table[i][j+1].group)) { // a!=b
                                    System.out.print("+"); // +
                                } else  if (!table[i+1][j].group.equals(table[i+1][j+1].group)) { //c!=d                                             // a =b
                                    System.out.print("+"); // +
                                } else {
                                    System.out.print("-"); // -
                                }
                            }
                        } else {                                                      //a = c
                            if (j != 0) {
                                System.out.print(" "); //" " add
                            }
                            System.out.print("          ");
                            if (j < 8) {
                                if (!table[i][j].group.equals(table[i][j+1].group)) { // a!=b
                                    if (table[i][j].group.equals(table[i+1][j].group) && table[i][j+1].group.equals(table[i+1][j+1].group)) {
                                        System.out.print("|");
                                    } else {
                                        System.out.print("+"); // +
                                    }
                                } else  if (!table[i+1][j].group.equals(table[i+1][j+1].group)) { //c!=d                                             // a =b
                                    System.out.print("+"); // +
                                } else {
                                    System.out.print(" "); // " "
                                }
                            }
                        }
                    }

                }
            }

            if (i < 8) {
                System.out.println();
            }
        }
    }

    /*
    Инициализация таблицы
     */
    private static void initializeTable (Var[][] table, int versionOfTableInitialization) {
        for (int i = 0; i < table.length; i++)        {
            for (int j = 0; j < table[0].length; j++)            {
                table[i][j] = new Var();
            }
        }

        if (versionOfTableInitialization == 1) { //23
            table[0][8].values = Integer.toString(6);
            table[1][1].values = Integer.toString(2);
            table[1][7].values = Integer.toString(5);
            table[2][6].values = Integer.toString(9);
            table[3][0].values = Integer.toString(1);
            table[3][7].values = Integer.toString(3);
            table[4][2].values = Integer.toString(4);
            table[4][6].values = Integer.toString(3);
            table[5][1].values = Integer.toString(4);
            table[5][8].values = Integer.toString(9);
            table[6][2].values = Integer.toString(2);
            table[7][1].values = Integer.toString(1);
            table[7][7].values = Integer.toString(4);
            table[8][0].values = Integer.toString(8);

            table[0][0].group = "group1";
            table[0][1].group = "group1";
            table[0][2].group = "group1";
            table[0][3].group = "group1";
            table[0][4].group = "group2";
            table[0][5].group = "group2";
            table[0][6].group = "group2";
            table[0][7].group = "group2";
            table[0][8].group = "group2";

            table[1][0].group = "group1";
            table[1][1].group = "group1";
            table[1][2].group = "group1";
            table[1][3].group = "group3";
            table[1][4].group = "group3";
            table[1][5].group = "group3";
            table[1][6].group = "group2";
            table[1][7].group = "group2";
            table[1][8].group = "group4";

            table[2][0].group = "group5";
            table[2][1].group = "group1";
            table[2][2].group = "group1";
            table[2][3].group = "group3";
            table[2][4].group = "group3";
            table[2][5].group = "group3";
            table[2][6].group = "group2";
            table[2][7].group = "group2";
            table[2][8].group = "group4";

            table[3][0].group = "group5";
            table[3][1].group = "group6";
            table[3][2].group = "group6";
            table[3][3].group = "group6";
            table[3][4].group = "group6";
            table[3][5].group = "group3";
            table[3][6].group = "group4";
            table[3][7].group = "group4";
            table[3][8].group = "group4";

            table[4][0].group = "group5";
            table[4][1].group = "group5";
            table[4][2].group = "group7";
            table[4][3].group = "group7";
            table[4][4].group = "group6";
            table[4][5].group = "group3";
            table[4][6].group = "group3";
            table[4][7].group = "group4";
            table[4][8].group = "group4";

            table[5][0].group = "group5";
            table[5][1].group = "group5";
            table[5][2].group = "group5";
            table[5][3].group = "group7";
            table[5][4].group = "group6";
            table[5][5].group = "group6";
            table[5][6].group = "group6";
            table[5][7].group = "group6";
            table[5][8].group = "group4";

            table[6][0].group = "group5";
            table[6][1].group = "group8";
            table[6][2].group = "group8";
            table[6][3].group = "group7";
            table[6][4].group = "group7";
            table[6][5].group = "group7";
            table[6][6].group = "group9";
            table[6][7].group = "group9";
            table[6][8].group = "group4";

            table[7][0].group = "group5";
            table[7][1].group = "group8";
            table[7][2].group = "group8";
            table[7][3].group = "group7";
            table[7][4].group = "group7";
            table[7][5].group = "group7";
            table[7][6].group = "group9";
            table[7][7].group = "group9";
            table[7][8].group = "group9";

            table[8][0].group = "group8";
            table[8][1].group = "group8";
            table[8][2].group = "group8";
            table[8][3].group = "group8";
            table[8][4].group = "group8";
            table[8][5].group = "group9";
            table[8][6].group = "group9";
            table[8][7].group = "group9";
            table[8][8].group = "group9";

            table[1][1].check = "check1";
            table[1][2].check = "check1";
            table[1][3].check = "check1";
            table[2][1].check = "check1";
            table[2][2].check = "check1";
            table[2][3].check = "check1";
            table[3][1].check = "check1";
            table[3][2].check = "check1";
            table[3][3].check = "check1";

            table[5][1].check = "check2";
            table[5][2].check = "check2";
            table[5][3].check = "check2";
            table[6][1].check = "check2";
            table[6][2].check = "check2";
            table[6][3].check = "check2";
            table[7][1].check = "check2";
            table[7][2].check = "check2";
            table[7][3].check = "check2";

            table[1][5].check = "check3";
            table[1][6].check = "check3";
            table[1][7].check = "check3";
            table[2][5].check = "check3";
            table[2][6].check = "check3";
            table[2][7].check = "check3";
            table[3][5].check = "check3";
            table[3][6].check = "check3";
            table[3][7].check = "check3";

            table[5][5].check = "check4";
            table[5][6].check = "check4";
            table[5][7].check = "check4";
            table[6][5].check = "check4";
            table[6][6].check = "check4";
            table[6][7].check = "check4";
            table[7][5].check = "check4";
            table[7][6].check = "check4";
            table[7][7].check = "check4";
        }

        if (versionOfTableInitialization == 2) { //7
            table[0][0].values = Integer.toString(5);
            table[2][0].values = Integer.toString(4);
            table[3][0].values = Integer.toString(8);
            table[2][1].values = Integer.toString(8);
            table[6][1].values = Integer.toString(6);
            table[7][1].values = Integer.toString(5);
            table[1][2].values = Integer.toString(3);
            table[3][2].values = Integer.toString(2);
            table[8][2].values = Integer.toString(8);
            table[6][3].values = Integer.toString(4);
            table[0][4].values = Integer.toString(9);
            table[1][4].values = Integer.toString(4);
            table[3][4].values = Integer.toString(6);
            table[5][4].values = Integer.toString(7);
            table[7][4].values = Integer.toString(3);
            table[8][4].values = Integer.toString(2);
            table[2][5].values = Integer.toString(1);
            table[0][6].values = Integer.toString(4);
            table[5][6].values = Integer.toString(9);
            table[7][6].values = Integer.toString(7);
            table[1][7].values = Integer.toString(6);
            table[2][7].values = Integer.toString(9);
            table[6][7].values = Integer.toString(2);
            table[5][8].values = Integer.toString(4);
            table[6][8].values = Integer.toString(9);
            table[8][8].values = Integer.toString(1);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[0][1].group = "group1";
            table[1][1].group = "group1";
            table[2][1].group = "group1";
            table[0][2].group = "group1";
            table[1][2].group = "group1";
            table[2][2].group = "group1";

            table[3][0].group = "group2";
            table[4][0].group = "group2";
            table[5][0].group = "group2";
            table[3][1].group = "group2";
            table[4][1].group = "group2";
            table[5][1].group = "group2";
            table[3][2].group = "group2";
            table[4][2].group = "group2";
            table[5][2].group = "group2";

            table[6][0].group = "group3";
            table[7][0].group = "group3";
            table[8][0].group = "group3";
            table[6][1].group = "group3";
            table[7][1].group = "group3";
            table[8][1].group = "group3";
            table[6][2].group = "group3";
            table[7][2].group = "group3";
            table[8][2].group = "group3";

            table[0][3].group = "group4";
            table[1][3].group = "group4";
            table[2][3].group = "group4";
            table[0][4].group = "group4";
            table[1][4].group = "group4";
            table[2][4].group = "group4";
            table[0][5].group = "group4";
            table[1][5].group = "group4";
            table[2][5].group = "group4";

            table[3][3].group = "group5";
            table[4][3].group = "group5";
            table[5][3].group = "group5";
            table[3][4].group = "group5";
            table[4][4].group = "group5";
            table[5][4].group = "group5";
            table[3][5].group = "group5";
            table[4][5].group = "group5";
            table[5][5].group = "group5";

            table[6][3].group = "group6";
            table[7][3].group = "group6";
            table[8][3].group = "group6";
            table[6][4].group = "group6";
            table[7][4].group = "group6";
            table[8][4].group = "group6";
            table[6][5].group = "group6";
            table[7][5].group = "group6";
            table[8][5].group = "group6";

            table[0][6].group = "group7";
            table[1][6].group = "group7";
            table[2][6].group = "group7";
            table[0][7].group = "group7";
            table[1][7].group = "group7";
            table[2][7].group = "group7";
            table[0][8].group = "group7";
            table[1][8].group = "group7";
            table[2][8].group = "group7";

            table[3][6].group = "group8";
            table[4][6].group = "group8";
            table[5][6].group = "group8";
            table[3][7].group = "group8";
            table[4][7].group = "group8";
            table[5][7].group = "group8";
            table[3][8].group = "group8";
            table[4][8].group = "group8";
            table[5][8].group = "group8";

            table[6][6].group = "group9";
            table[7][6].group = "group9";
            table[8][6].group = "group9";
            table[6][7].group = "group9";
            table[7][7].group = "group9";
            table[8][7].group = "group9";
            table[6][8].group = "group9";
            table[7][8].group = "group9";
            table[8][8].group = "group9";
        }

        if (versionOfTableInitialization == 3) { //???
            table[8][0].values = Integer.toString(7);
            table[0][1].values = Integer.toString(5);
            table[4][1].values = Integer.toString(4);
            table[5][1].values = Integer.toString(2);
            table[2][2].values = Integer.toString(1);
            table[4][2].values = Integer.toString(8);
            table[8][2].values = Integer.toString(6);
            table[1][4].values = Integer.toString(2);
            table[7][4].values = Integer.toString(9);
            table[0][6].values = Integer.toString(4);
            table[4][6].values = Integer.toString(7);
            table[6][6].values = Integer.toString(1);
            table[3][7].values = Integer.toString(2);
            table[4][7].values = Integer.toString(6);
            table[8][7].values = Integer.toString(8);
            table[0][8].values = Integer.toString(8);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[3][0].group = "group1";
            table[4][0].group = "group1";
            table[0][1].group = "group1";
            table[1][1].group = "group1";
            table[1][2].group = "group1";
            table[2][2].group = "group1";

            table[0][2].group = "group2";
            table[0][3].group = "group2";
            table[1][3].group = "group2";
            table[2][3].group = "group2";
            table[0][4].group = "group2";
            table[1][4].group = "group2";
            table[2][4].group = "group2";
            table[3][4].group = "group2";
            table[0][5].group = "group2";

            table[1][5].group = "group3";
            table[2][5].group = "group3";
            table[0][6].group = "group3";
            table[1][6].group = "group3";
            table[0][7].group = "group3";
            table[1][7].group = "group3";
            table[0][8].group = "group3";
            table[1][8].group = "group3";
            table[2][8].group = "group3";

            table[5][0].group = "group4";
            table[2][1].group = "group4";
            table[3][1].group = "group4";
            table[4][1].group = "group4";
            table[5][1].group = "group4";
            table[6][1].group = "group4";
            table[3][2].group = "group4";
            table[4][2].group = "group4";
            table[3][3].group = "group4";

            table[5][2].group = "group5";
            table[6][2].group = "group5";
            table[4][3].group = "group5";
            table[5][3].group = "group5";
            table[4][4].group = "group5";
            table[3][5].group = "group5";
            table[4][5].group = "group5";
            table[2][6].group = "group5";
            table[3][6].group = "group5";

            table[5][5].group = "group6";
            table[4][6].group = "group6";
            table[5][6].group = "group6";
            table[2][7].group = "group6";
            table[3][7].group = "group6";
            table[4][7].group = "group6";
            table[5][7].group = "group6";
            table[6][7].group = "group6";
            table[3][8].group = "group6";

            table[6][0].group = "group7";
            table[7][0].group = "group7";
            table[8][0].group = "group7";
            table[7][1].group = "group7";
            table[8][1].group = "group7";
            table[7][2].group = "group7";
            table[8][2].group = "group7";
            table[6][3].group = "group7";
            table[7][3].group = "group7";

            table[8][3].group = "group8";
            table[5][4].group = "group8";
            table[6][4].group = "group8";
            table[7][4].group = "group8";
            table[8][4].group = "group8";
            table[6][5].group = "group8";
            table[7][5].group = "group8";
            table[8][5].group = "group8";
            table[8][6].group = "group8";

            table[6][6].group = "group9";
            table[7][6].group = "group9";
            table[7][7].group = "group9";
            table[8][7].group = "group9";
            table[4][8].group = "group9";
            table[5][8].group = "group9";
            table[6][8].group = "group9";
            table[7][8].group = "group9";
            table[8][8].group = "group9";

            table[0][0].check = "check1";
            table[3][0].check = "check1";
            table[6][0].check = "check1";
            table[0][3].check = "check1";
            table[3][3].check = "check1";
            table[6][3].check = "check1";
            table[0][6].check = "check1";
            table[3][6].check = "check1";
            table[6][6].check = "check1";

            table[1][0].check = "check2";
            table[4][0].check = "check2";
            table[7][0].check = "check2";
            table[1][3].check = "check2";
            table[4][3].check = "check2";
            table[7][3].check = "check2";
            table[1][6].check = "check2";
            table[4][6].check = "check2";
            table[7][6].check = "check2";

            table[2][0].check = "check3";
            table[5][0].check = "check3";
            table[8][0].check = "check3";
            table[2][3].check = "check3";
            table[5][3].check = "check3";
            table[8][3].check = "check3";
            table[2][6].check = "check3";
            table[5][6].check = "check3";
            table[8][6].check = "check3";

            table[0][1].check = "check4";
            table[3][1].check = "check4";
            table[6][1].check = "check4";
            table[0][4].check = "check4";
            table[3][4].check = "check4";
            table[6][4].check = "check4";
            table[0][7].check = "check4";
            table[3][7].check = "check4";
            table[6][7].check = "check4";

            table[1][1].check = "check5";
            table[4][1].check = "check5";
            table[7][1].check = "check5";
            table[1][4].check = "check5";
            table[4][4].check = "check5";
            table[7][4].check = "check5";
            table[1][7].check = "check5";
            table[4][7].check = "check5";
            table[7][7].check = "check5";

            table[2][1].check = "check6";
            table[5][1].check = "check6";
            table[8][1].check = "check6";
            table[2][4].check = "check6";
            table[5][4].check = "check6";
            table[8][4].check = "check6";
            table[2][7].check = "check6";
            table[5][7].check = "check6";
            table[8][7].check = "check6";

            table[0][2].check = "check7";
            table[3][2].check = "check7";
            table[6][2].check = "check7";
            table[0][5].check = "check7";
            table[3][5].check = "check7";
            table[6][5].check = "check7";
            table[0][8].check = "check7";
            table[3][8].check = "check7";
            table[6][8].check = "check7";

            table[1][2].check = "check8";
            table[4][2].check = "check8";
            table[7][2].check = "check8";
            table[1][5].check = "check8";
            table[4][5].check = "check8";
            table[7][5].check = "check8";
            table[1][8].check = "check8";
            table[4][8].check = "check8";
            table[7][8].check = "check8";

            table[2][2].check = "check9";
            table[5][2].check = "check9";
            table[8][2].check = "check9";
            table[2][5].check = "check9";
            table[5][5].check = "check9";
            table[8][5].check = "check9";
            table[2][8].check = "check9";
            table[5][8].check = "check9";
            table[8][8].check = "check9";
        }

        if (versionOfTableInitialization == 4) { //25
            table[3][0].values = Integer.toString(9);
            table[4][0].values = Integer.toString(2);
            table[3][1].values = Integer.toString(4);
            table[3][2].values = Integer.toString(3);
            table[6][3].values = Integer.toString(5);
            table[1][4].values = Integer.toString(1);
            table[3][4].values = Integer.toString(2);
            table[4][4].values = Integer.toString(7);
            table[5][4].values = Integer.toString(4);
            table[7][4].values = Integer.toString(6);
            table[2][5].values = Integer.toString(4);
            table[5][6].values = Integer.toString(5);
            table[5][7].values = Integer.toString(7);
            table[4][8].values = Integer.toString(6);
            table[5][8].values = Integer.toString(2);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[3][0].group = "group1";
            table[4][0].group = "group1";
            table[5][0].group = "group6";
            table[6][0].group = "group6";
            table[7][0].group = "group6";
            table[8][0].group = "group8";
            table[0][1].group = "group1";
            table[1][1].group = "group2";
            table[2][1].group = "group1";
            table[3][1].group = "group1";
            table[4][1].group = "group1";
            table[5][1].group = "group6";
            table[6][1].group = "group6";
            table[7][1].group = "group8";
            table[8][1].group = "group8";
            table[0][2].group = "group2";
            table[1][2].group = "group2";
            table[2][2].group = "group2";
            table[3][2].group = "group2";
            table[4][2].group = "group6";
            table[5][2].group = "group6";
            table[6][2].group = "group6";
            table[7][2].group = "group8";
            table[8][2].group = "group8";
            table[0][3].group = "group2";
            table[1][3].group = "group2";
            table[2][3].group = "group5";
            table[3][3].group = "group2";
            table[4][3].group = "group5";
            table[5][3].group = "group6";
            table[6][3].group = "group8";
            table[7][3].group = "group8";
            table[8][3].group = "group8";
            table[0][4].group = "group3";
            table[1][4].group = "group2";
            table[2][4].group = "group5";
            table[3][4].group = "group5";
            table[4][4].group = "group5";
            table[5][4].group = "group5";
            table[6][4].group = "group5";
            table[7][4].group = "group9";
            table[8][4].group = "group8";
            table[0][5].group = "group3";
            table[1][5].group = "group3";
            table[2][5].group = "group3";
            table[3][5].group = "group4";
            table[4][5].group = "group5";
            table[5][5].group = "group9";
            table[6][5].group = "group5";
            table[7][5].group = "group9";
            table[8][5].group = "group9";
            table[0][6].group = "group3";
            table[1][6].group = "group3";
            table[2][6].group = "group4";
            table[3][6].group = "group4";
            table[4][6].group = "group4";
            table[5][6].group = "group9";
            table[6][6].group = "group9";
            table[7][6].group = "group9";
            table[8][6].group = "group9";
            table[0][7].group = "group3";
            table[1][7].group = "group3";
            table[2][7].group = "group4";
            table[3][7].group = "group4";
            table[4][7].group = "group7";
            table[5][7].group = "group7";
            table[6][7].group = "group7";
            table[7][7].group = "group9";
            table[8][7].group = "group7";
            table[0][8].group = "group3";
            table[1][8].group = "group4";
            table[2][8].group = "group4";
            table[3][8].group = "group4";
            table[4][8].group = "group7";
            table[5][8].group = "group7";
            table[6][8].group = "group7";
            table[7][8].group = "group7";
            table[8][8].group = "group7";

            table[1][1].check = "check1";
            table[2][1].check = "check1";
            table[3][1].check = "check1";
            table[5][1].check = "check3";
            table[6][1].check = "check3";
            table[7][1].check = "check3";
            table[1][2].check = "check1";
            table[2][2].check = "check1";
            table[3][2].check = "check1";
            table[5][2].check = "check3";
            table[6][2].check = "check3";
            table[7][2].check = "check3";
            table[1][3].check = "check1";
            table[2][3].check = "check1";
            table[3][3].check = "check1";
            table[5][3].check = "check3";
            table[6][3].check = "check3";
            table[7][3].check = "check3";
            table[1][6].check = "check2";
            table[2][6].check = "check2";
            table[3][6].check = "check2";
            table[5][6].check = "check4";
            table[6][6].check = "check4";
            table[7][6].check = "check4";
            table[1][7].check = "check2";
            table[2][7].check = "check2";
            table[3][7].check = "check2";
            table[5][7].check = "check4";
            table[6][7].check = "check4";
            table[7][7].check = "check4";
            table[1][5].check = "check2";
            table[2][5].check = "check2";
            table[3][5].check = "check2";
            table[5][5].check = "check4";
            table[6][5].check = "check4";
            table[7][5].check = "check4";
        }

        if (versionOfTableInitialization == 5) { //27
            table[2][0].values = Integer.toString(3);
            table[5][0].values = Integer.toString(4);
            table[6][0].values = Integer.toString(6);
            table[2][1].values = Integer.toString(7);
            table[8][1].values = Integer.toString(5);
            table[0][2].values = Integer.toString(2);
            table[4][2].values = Integer.toString(8);
            table[6][3].values = Integer.toString(8);
            table[7][3].values = Integer.toString(9);
            table[8][3].values = Integer.toString(7);
            table[0][5].values = Integer.toString(7);
            table[1][5].values = Integer.toString(9);
            table[2][5].values = Integer.toString(4);
            table[4][6].values = Integer.toString(4);
            table[8][6].values = Integer.toString(2);
            table[0][7].values = Integer.toString(5);
            table[6][7].values = Integer.toString(7);
            table[2][8].values = Integer.toString(8);
            table[3][8].values = Integer.toString(4);
            table[6][8].values = Integer.toString(9);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[3][0].group = "group4";
            table[4][0].group = "group4";
            table[5][0].group = "group4";
            table[6][0].group = "group7";
            table[7][0].group = "group7";
            table[8][0].group = "group9";
            table[0][1].group = "group1";
            table[1][1].group = "group1";
            table[2][1].group = "group4";
            table[3][1].group = "group4";
            table[4][1].group = "group5";
            table[5][1].group = "group4";
            table[6][1].group = "group7";
            table[7][1].group = "group7";
            table[8][1].group = "group9";
            table[0][2].group = "group2";
            table[1][2].group = "group1";
            table[2][2].group = "group1";
            table[3][2].group = "group4";
            table[4][2].group = "group5";
            table[5][2].group = "group7";
            table[6][2].group = "group7";
            table[7][2].group = "group9";
            table[8][2].group = "group9";
            table[0][3].group = "group2";
            table[1][3].group = "group2";
            table[2][3].group = "group1";
            table[3][3].group = "group4";
            table[4][3].group = "group5";
            table[5][3].group = "group5";
            table[6][3].group = "group7";
            table[7][3].group = "group7";
            table[8][3].group = "group9";
            table[0][4].group = "group2";
            table[1][4].group = "group3";
            table[2][4].group = "group1";
            table[3][4].group = "group4";
            table[4][4].group = "group5";
            table[5][4].group = "group6";
            table[6][4].group = "group8";
            table[7][4].group = "group7";
            table[8][4].group = "group9";
            table[0][5].group = "group2";
            table[1][5].group = "group3";
            table[2][5].group = "group3";
            table[3][5].group = "group5";
            table[4][5].group = "group5";
            table[5][5].group = "group6";
            table[6][5].group = "group8";
            table[7][5].group = "group9";
            table[8][5].group = "group9";
            table[0][6].group = "group2";
            table[1][6].group = "group2";
            table[2][6].group = "group3";
            table[3][6].group = "group3";
            table[4][6].group = "group5";
            table[5][6].group = "group6";
            table[6][6].group = "group8";
            table[7][6].group = "group8";
            table[8][6].group = "group9";
            table[0][7].group = "group2";
            table[1][7].group = "group3";
            table[2][7].group = "group3";
            table[3][7].group = "group6";
            table[4][7].group = "group5";
            table[5][7].group = "group6";
            table[6][7].group = "group6";
            table[7][7].group = "group8";
            table[8][7].group = "group8";
            table[0][8].group = "group2";
            table[1][8].group = "group3";
            table[2][8].group = "group3";
            table[3][8].group = "group6";
            table[4][8].group = "group6";
            table[5][8].group = "group6";
            table[6][8].group = "group8";
            table[7][8].group = "group8";
            table[8][8].group = "group8";

            table[1][1].check = "check1";
            table[2][1].check = "check1";
            table[3][1].check = "check1";
            table[5][1].check = "check3";
            table[6][1].check = "check3";
            table[7][1].check = "check3";
            table[1][2].check = "check1";
            table[2][2].check = "check1";
            table[3][2].check = "check1";
            table[5][2].check = "check3";
            table[6][2].check = "check3";
            table[7][2].check = "check3";
            table[1][3].check = "check1";
            table[2][3].check = "check1";
            table[3][3].check = "check1";
            table[5][3].check = "check3";
            table[6][3].check = "check3";
            table[7][3].check = "check3";
            table[1][5].check = "check2";
            table[2][5].check = "check2";
            table[3][5].check = "check2";
            table[5][5].check = "check4";
            table[6][5].check = "check4";
            table[7][5].check = "check4";
            table[1][6].check = "check2";
            table[2][6].check = "check2";
            table[3][6].check = "check2";
            table[5][6].check = "check4";
            table[6][6].check = "check4";
            table[7][6].check = "check4";
            table[1][7].check = "check2";
            table[2][7].check = "check2";
            table[3][7].check = "check2";
            table[5][7].check = "check4";
            table[6][7].check = "check4";
            table[7][7].check = "check4";
        }

        if (versionOfTableInitialization == 6) {
            table[4][0].values = Integer.toString(9);
            table[6][0].values = Integer.toString(1);
            table[0][1].values = Integer.toString(1);
            table[3][1].values = Integer.toString(9);
            table[4][1].values = Integer.toString(7);
            table[6][1].values = Integer.toString(3);
            table[0][3].values = Integer.toString(2);
            table[3][4].values = Integer.toString(2);
            table[5][4].values = Integer.toString(3);
            table[8][5].values = Integer.toString(3);
            table[2][7].values = Integer.toString(9);
            table[4][7].values = Integer.toString(1);
            table[5][7].values = Integer.toString(6);
            table[8][7].values = Integer.toString(4);
            table[2][8].values = Integer.toString(6);
            table[4][8].values = Integer.toString(5);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[3][0].group = "group5";
            table[4][0].group = "group5";
            table[5][0].group = "group5";
            table[6][0].group = "group5";
            table[7][0].group = "group5";
            table[8][0].group = "group7";
            table[0][1].group = "group1";
            table[1][1].group = "group1";
            table[2][1].group = "group1";
            table[3][1].group = "group1";
            table[4][1].group = "group6";
            table[5][1].group = "group5";
            table[6][1].group = "group5";
            table[7][1].group = "group5";
            table[8][1].group = "group7";
            table[0][2].group = "group1";
            table[1][2].group = "group1";
            table[2][2].group = "group2";
            table[3][2].group = "group2";
            table[4][2].group = "group6";
            table[5][2].group = "group6";
            table[6][2].group = "group7";
            table[7][2].group = "group5";
            table[8][2].group = "group7";
            table[0][3].group = "group2";
            table[1][3].group = "group2";
            table[2][3].group = "group2";
            table[3][3].group = "group2";
            table[4][3].group = "group6";
            table[5][3].group = "group7";
            table[6][3].group = "group7";
            table[7][3].group = "group7";
            table[8][3].group = "group7";
            table[0][4].group = "group2";
            table[1][4].group = "group2";
            table[2][4].group = "group2";
            table[3][4].group = "group3";
            table[4][4].group = "group6";
            table[5][4].group = "group7";
            table[6][4].group = "group8";
            table[7][4].group = "group8";
            table[8][4].group = "group8";
            table[0][5].group = "group3";
            table[1][5].group = "group3";
            table[2][5].group = "group3";
            table[3][5].group = "group3";
            table[4][5].group = "group6";
            table[5][5].group = "group8";
            table[6][5].group = "group8";
            table[7][5].group = "group8";
            table[8][5].group = "group8";
            table[0][6].group = "group3";
            table[1][6].group = "group4";
            table[2][6].group = "group3";
            table[3][6].group = "group6";
            table[4][6].group = "group6";
            table[5][6].group = "group8";
            table[6][6].group = "group8";
            table[7][6].group = "group9";
            table[8][6].group = "group9";
            table[0][7].group = "group3";
            table[1][7].group = "group4";
            table[2][7].group = "group4";
            table[3][7].group = "group4";
            table[4][7].group = "group6";
            table[5][7].group = "group9";
            table[6][7].group = "group9";
            table[7][7].group = "group9";
            table[8][7].group = "group9";
            table[0][8].group = "group3";
            table[1][8].group = "group4";
            table[2][8].group = "group4";
            table[3][8].group = "group4";
            table[4][8].group = "group4";
            table[5][8].group = "group4";
            table[6][8].group = "group9";
            table[7][8].group = "group9";
            table[8][8].group = "group9";

            table[1][1].check = "check1";
            table[2][1].check = "check1";
            table[3][1].check = "check1";
            table[5][1].check = "check3";
            table[6][1].check = "check3";
            table[7][1].check = "check3";
            table[1][2].check = "check1";
            table[2][2].check = "check1";
            table[3][2].check = "check1";
            table[5][2].check = "check3";
            table[6][2].check = "check3";
            table[7][2].check = "check3";
            table[1][3].check = "check1";
            table[2][3].check = "check1";
            table[3][3].check = "check1";
            table[5][3].check = "check3";
            table[6][3].check = "check3";
            table[7][3].check = "check3";
            table[1][5].check = "check2";
            table[2][5].check = "check2";
            table[3][5].check = "check2";
            table[5][5].check = "check4";
            table[6][5].check = "check4";
            table[7][5].check = "check4";
            table[1][6].check = "check2";
            table[2][6].check = "check2";
            table[3][6].check = "check2";
            table[5][6].check = "check4";
            table[6][6].check = "check4";
            table[7][6].check = "check4";
            table[1][7].check = "check2";
            table[2][7].check = "check2";
            table[3][7].check = "check2";
            table[5][7].check = "check4";
            table[6][7].check = "check4";
            table[7][7].check = "check4";
        }

        if (versionOfTableInitialization == 7) { //17
            table[2][0].values = Integer.toString(5);
            table[7][0].values = Integer.toString(6);
            table[1][1].values = Integer.toString(1);
            table[3][1].values = Integer.toString(6);
            table[4][1].values = Integer.toString(5);
            table[7][1].values = Integer.toString(9);
            table[5][2].values = Integer.toString(9);
            table[0][4].values = Integer.toString(7);
            table[1][4].values = Integer.toString(8);
            table[7][4].values = Integer.toString(4);
            table[8][4].values = Integer.toString(1);
            table[3][6].values = Integer.toString(4);
            table[1][7].values = Integer.toString(3);
            table[4][7].values = Integer.toString(2);
            table[5][7].values = Integer.toString(8);
            table[7][7].values = Integer.toString(7);
            table[1][8].values = Integer.toString(9);
            table[6][8].values = Integer.toString(4);

            table[0][0].group = "group1";
            table[1][0].group = "group1";
            table[2][0].group = "group1";
            table[3][0].group = "group1";
            table[4][0].group = "group1";
            table[5][0].group = "group6";
            table[6][0].group = "group6";
            table[7][0].group = "group6";
            table[8][0].group = "group6";
            table[0][1].group = "group1";
            table[1][1].group = "group2";
            table[2][1].group = "group1";
            table[3][1].group = "group1";
            table[4][1].group = "group6";
            table[5][1].group = "group6";
            table[6][1].group = "group7";
            table[7][1].group = "group7";
            table[8][1].group = "group7";
            table[0][2].group = "group2";
            table[1][2].group = "group2";
            table[2][2].group = "group2";
            table[3][2].group = "group1";
            table[4][2].group = "group6";
            table[5][2].group = "group6";
            table[6][2].group = "group7";
            table[7][2].group = "group7";
            table[8][2].group = "group7";
            table[0][3].group = "group2";
            table[1][3].group = "group2";
            table[2][3].group = "group2";
            table[3][3].group = "group5";
            table[4][3].group = "group5";
            table[5][3].group = "group6";
            table[6][3].group = "group7";
            table[7][3].group = "group7";
            table[8][3].group = "group7";
            table[0][4].group = "group2";
            table[1][4].group = "group2";
            table[2][4].group = "group5";
            table[3][4].group = "group5";
            table[4][4].group = "group5";
            table[5][4].group = "group5";
            table[6][4].group = "group5";
            table[7][4].group = "group8";
            table[8][4].group = "group8";
            table[0][5].group = "group3";
            table[1][5].group = "group3";
            table[2][5].group = "group3";
            table[3][5].group = "group4";
            table[4][5].group = "group5";
            table[5][5].group = "group5";
            table[6][5].group = "group8";
            table[7][5].group = "group8";
            table[8][5].group = "group8";
            table[0][6].group = "group3";
            table[1][6].group = "group3";
            table[2][6].group = "group3";
            table[3][6].group = "group4";
            table[4][6].group = "group4";
            table[5][6].group = "group9";
            table[6][6].group = "group8";
            table[7][6].group = "group8";
            table[8][6].group = "group8";
            table[0][7].group = "group3";
            table[1][7].group = "group3";
            table[2][7].group = "group3";
            table[3][7].group = "group4";
            table[4][7].group = "group4";
            table[5][7].group = "group9";
            table[6][7].group = "group9";
            table[7][7].group = "group8";
            table[8][7].group = "group9";
            table[0][8].group = "group4";
            table[1][8].group = "group4";
            table[2][8].group = "group4";
            table[3][8].group = "group4";
            table[4][8].group = "group9";
            table[5][8].group = "group9";
            table[6][8].group = "group9";
            table[7][8].group = "group9";
            table[8][8].group = "group9";

            table[0][0].check = "check1";
            table[3][0].check = "check1";
            table[6][0].check = "check1";
            table[0][3].check = "check1";
            table[3][3].check = "check1";
            table[6][3].check = "check1";
            table[0][6].check = "check1";
            table[3][6].check = "check1";
            table[6][6].check = "check1";

            table[1][0].check = "check2";
            table[4][0].check = "check2";
            table[7][0].check = "check2";
            table[1][3].check = "check2";
            table[4][3].check = "check2";
            table[7][3].check = "check2";
            table[1][6].check = "check2";
            table[4][6].check = "check2";
            table[7][6].check = "check2";

            table[2][0].check = "check3";
            table[5][0].check = "check3";
            table[8][0].check = "check3";
            table[2][3].check = "check3";
            table[5][3].check = "check3";
            table[8][3].check = "check3";
            table[2][6].check = "check3";
            table[5][6].check = "check3";
            table[8][6].check = "check3";

            table[0][1].check = "check4";
            table[3][1].check = "check4";
            table[6][1].check = "check4";
            table[0][4].check = "check4";
            table[3][4].check = "check4";
            table[6][4].check = "check4";
            table[0][7].check = "check4";
            table[3][7].check = "check4";
            table[6][7].check = "check4";

            table[1][1].check = "check5";
            table[4][1].check = "check5";
            table[7][1].check = "check5";
            table[1][4].check = "check5";
            table[4][4].check = "check5";
            table[7][4].check = "check5";
            table[1][7].check = "check5";
            table[4][7].check = "check5";
            table[7][7].check = "check5";

            table[2][1].check = "check6";
            table[5][1].check = "check6";
            table[8][1].check = "check6";
            table[2][4].check = "check6";
            table[5][4].check = "check6";
            table[8][4].check = "check6";
            table[2][7].check = "check6";
            table[5][7].check = "check6";
            table[8][7].check = "check6";

            table[0][2].check = "check7";
            table[3][2].check = "check7";
            table[6][2].check = "check7";
            table[0][5].check = "check7";
            table[3][5].check = "check7";
            table[6][5].check = "check7";
            table[0][8].check = "check7";
            table[3][8].check = "check7";
            table[6][8].check = "check7";

            table[1][2].check = "check8";
            table[4][2].check = "check8";
            table[7][2].check = "check8";
            table[1][5].check = "check8";
            table[4][5].check = "check8";
            table[7][5].check = "check8";
            table[1][8].check = "check8";
            table[4][8].check = "check8";
            table[7][8].check = "check8";

            table[2][2].check = "check9";
            table[5][2].check = "check9";
            table[8][2].check = "check9";
            table[2][5].check = "check9";
            table[5][5].check = "check9";
            table[8][5].check = "check9";
            table[2][8].check = "check9";
            table[5][8].check = "check9";
            table[8][8].check = "check9";
        }
    }

    /*
    Получений элемента дерева по его идентификатору
     */
    private static TableTree getTableNode(int iterationNumber) {
        TableTree tableNod = null;
        for (TableTree tableTreeElement : tableTreeList) {
            if (tableTreeElement.iterationNumber == iterationNumber) {
                tableNod = tableTreeElement;
            }
        }
        return tableNod;
    }

    /*
    Sequence (Получение нового не используемого номер итератора)
     */
    private static int getNewIterationNumber (int iterationNumber) {
        int newIterationNumber = iterationNumber + 1;
        for (int i = 0; i < tableTreeList.size(); i++)
        {
            if (newIterationNumber == tableTreeList.get(i).iterationNumber) {
                return getNewIterationNumber(newIterationNumber);
            }
        }
        return newIterationNumber;
    }

    static class Var {
        String values;
        String group;
        String check;
        int x;
        int y;
    }

    static class Rule {
        String group;
        String check;
        boolean line;
        int value;
        int lineNumber;

        Rule (String group, String check, boolean line, int value, int lineNumber) {
            this.group = group;
            this.check = check;
            this.line = line;
            this.value = value;
            this.lineNumber = lineNumber;
        }
    }

    static class TableTree {
        Var[][] table;
        int iterationNumber;
        int parentIterationNumber;
        int cellX;
        int cellY;
        String valueToTry;
        byte status; // 0 - needToTryAgain 1 - error 2 - ok
        List<Integer> childrenIterationList;

        TableTree (Var[][] table, int iterationNumber, int parentIterationNumber, int cellX, int cellY,
                   String valueToTry) {
            this.table = new Var[9][9];
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    this.table[i][j] = new Var();
                    this.table[i][j].values = table[i][j].values;
                    this.table[i][j].group = table[i][j].group;
                    if (table[i][j].check != null) {
                        this.table[i][j].check = table[i][j].check;
                    }
                }
            }
            this.iterationNumber = iterationNumber;
            this.parentIterationNumber = parentIterationNumber;
            this.cellX = cellX;
            this.cellY = cellY;
            this.valueToTry = valueToTry;
            this.childrenIterationList = new ArrayList<>();
        }

        TableTree (Var[][] table, int iterationNumber) {
            this.table = table;
            this.iterationNumber = iterationNumber;
            this.parentIterationNumber = -1;
            this.childrenIterationList = new ArrayList<>();
        }

        void setStatus (byte status) {
            this.status = status;
        }


    }

}
