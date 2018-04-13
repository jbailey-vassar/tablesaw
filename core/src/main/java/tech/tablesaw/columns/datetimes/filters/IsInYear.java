/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.tablesaw.columns.datetimes.filters;

import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.ColumnReference;
import tech.tablesaw.columns.dates.PackedLocalDate;
import tech.tablesaw.filtering.ColumnFilter;
import tech.tablesaw.selection.Selection;

import static tech.tablesaw.columns.datetimes.DateTimePredicates.isInYear;

public class IsInYear extends ColumnFilter {

    private final int year;

    public IsInYear(ColumnReference reference, int year) {
        super(reference);
        this.year = year;
    }

    @Override
    public Selection apply(Table relation) {
        String name = columnReference().getColumnName();
        Column column = relation.column(name);
        return apply(column);
    }

    @Override
    public Selection apply(Column columnBeingFiltered) {
        ColumnType type = columnBeingFiltered.type();
        switch (type) {
            case LOCAL_DATE:
                DateColumn dateColumn = (DateColumn) columnBeingFiltered;
                return dateColumn.eval(PackedLocalDate::isInYear, year);
            case LOCAL_DATE_TIME:
                DateTimeColumn dateTimeColumn = (DateTimeColumn) columnBeingFiltered;
                return dateTimeColumn.eval(isInYear, year);
            default:
                throw new UnsupportedOperationException("Columns of type " + type.name() + " do not support the operation "
                        + "isInYear(anInt) ");
        }
    }
}
