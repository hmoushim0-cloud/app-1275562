package sa.factory.iiotindustrialiot1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IiotIndustrialEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun industrial_iotDao(): IiotIndustrialDao
}
