package com.mexicandeveloper.iplocation;

import android.app.Application;

import com.mexicandeveloper.iplocation.db.IpLocationDB;
import com.mexicandeveloper.iplocation.db.IpLocationDao;
import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;
import com.mexicandeveloper.iplocation.model.LocationResponse;
import com.mexicandeveloper.iplocation.network.LocationIpService;
import com.mexicandeveloper.iplocation.repository.Repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    @Mock
    private IpLocationDao mockDataDao;

    @Mock
    private LocationIpService mockRetrofitService;

    @Mock
    private Application mockApplication;

    private Repository dataRepository;

    @Before
    public void setUp() {
        IpLocationDB mockDatabase = Mockito.mock(IpLocationDB.class);
        Mockito.when(mockDatabase.ipLocationDao()).thenReturn(mockDataDao);
        Mockito.when(mockApplication.getApplicationContext()).thenReturn(mockApplication);
        dataRepository = new Repository(mockDataDao,mockRetrofitService);

    }

    @Test
    public void testGetData_whenDataInDatabase() {
        IPGeolocationEntity dataEntity = new IPGeolocationEntity(
                 "1",
                1000123121L,
                 "success",
                 "United States",
                 "US",
                 "TN",
                 "Tennessee",
                 "Memphis",
                 "38108",
                 35.1579,
                 -89.9701,
                 "America/Chicago",
                 "Windstream Communications LLC",
                 "Colco Fine Woods Tool",
                 ""
        );

        Mockito.when(mockDataDao.retrieveLocation("1")).thenReturn(Single.just(dataEntity));

        dataRepository.getIPLocation("1")
                .test()
                .assertValue(dataEntity)
                .assertComplete();
    }

    @Test
    public void testGetData_whenDataNotInDatabase_andFetchFromNetwork() {
        IPGeolocationEntity dataEntity = new IPGeolocationEntity(
                "1",
                1000123121L,
                "success",
                "United States",
                "US",
                "TN",
                "Tennessee",
                "Memphis",
                "38108",
                35.1579,
                -89.9701,
                "America/Chicago",
                "Windstream Communications LLC",
                "Colco Fine Woods Tool",
                ""
        );
        LocationResponse apiResponse = new LocationResponse();
        apiResponse.setStatus("success");
        apiResponse.setQuery("1");

        Mockito.when(mockDataDao.retrieveLocation("1")).thenReturn(Single.error(new Throwable("No data")));
        Mockito.when(mockRetrofitService.getLocation("1")).thenReturn(Single.just(apiResponse));
        Mockito.doNothing().when(mockDataDao).insertIPLocation(dataEntity);

        dataRepository.getIPLocation("1")
                .test()
                .assertValue(dataEntity)
                .assertComplete();
    }

    @Test
    public void testGetData_whenApiResponseStatusFail() {
        LocationResponse apiResponse = new LocationResponse();
        apiResponse.setStatus("fail");
        apiResponse.setMessage("API error");

        Mockito.when(mockDataDao.retrieveLocation("1")).thenReturn(Single.error(new Throwable("No data")));
        Mockito.when(mockRetrofitService.getLocation("1")).thenReturn(Single.just(apiResponse));

        dataRepository.getIPLocation("1")
                .test()
                .assertError(Throwable.class)
                .assertErrorMessage("API error");
    }
}

