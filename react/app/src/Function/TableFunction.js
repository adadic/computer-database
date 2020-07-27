function descendingComparatorCompany(a, b, orderBy) {

    const first = a[orderBy];
    const second = b[orderBy];

    if (second.toUpperCase() < first.toUpperCase()) {
        return -1;
    }
    if (second.toUpperCase() > first.toUpperCase()) {
        return 1;
    }
    return 0;
}

export function getComparatorCompany(order, orderBy) {

    return order === 'desc'
        ? (a, b) => descendingComparatorCompany(a, b, orderBy)
        : (a, b) => -descendingComparatorCompany(a, b, orderBy);
}

export function getComparator(order, orderBy) {

    return order === 'desc'
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
}

function descendingComparator(a, b, orderBy) {

    const first = a[orderBy];
    const second = b[orderBy];
    if (orderBy === "company") {
        if (!second["name"] && !first["name"]) {
            return 0;
        } else if (!second["name"]) {
            return 1;
        } else if (!first["name"]) {
            return -1;
        } else if (second["name"].toUpperCase() < first["name"].toUpperCase()) {
            return -1;
        } else if (second["name"].toUpperCase() > first["name"].toUpperCase()) {
            return 1;
        }
    } else {
        if (!second && !first) {
            return 0;
        }
        if (!second) {
            return 1;
        }
        if (!first) {
            return -1;
        }
        if (orderBy === "name") {
            if (second.toUpperCase() < first.toUpperCase()) {
                return -1;
            }
            if (second.toUpperCase() > first.toUpperCase()) {
                return 1;
            }
        } else {
            if (new Date(second.year, second.monthValue, second.dayOfMonth) < new Date(first.year, first.monthValue, first.dayOfMonth)) {
                return -1;
            }
            if (new Date(second.year, second.monthValue, second.dayOfMonth) > new Date(first.year, first.monthValue, first.dayOfMonth)) {
                return 1;
            }
        }
    }
    return 0;
}

export function stableSort(array, comparator) {

    const stabilizedThis = array.map((el, index) => [el, index]);
    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) return order;
        return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
}