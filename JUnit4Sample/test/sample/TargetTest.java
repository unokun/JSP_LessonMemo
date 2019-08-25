package sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TargetTest {
	Target target;

	//@Mock
	Target2 target2;

	@Before
	public void before() {
		// テスト前処理実行
		target = new Target();
	}
	@After
	public void tearDown() {
		// テスト後処理実行
	}

	@Test
	public void testSum() {
		try {
			assertThat(target.sum(2, 3), is(5));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// privateメソッドのテスト
	// https://qiita.com/village/items/2f0d99b25eef0c8fd4ec
	public void testSump() {
		try {
			Method method = Target.class.getDeclaredMethod("sump", int.class, int.class);
			method.setAccessible(true);
			int actual = (int)method.invoke(target, 2, 3);
			assertThat(actual, is(5));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// Mockを使ったテスト
	// https://qiita.com/mstssk/items/98e597c13f12746c907d
	public void testAdd() {
		try {
			// メソッドを置き換えMock用のクラスを返却する
			target = spy(new Target());
			doReturn(target2).when(target.createTarget2());

			// Mock(入力にかかわらず返却値を設定する
			doReturn(true).when(target2).isEqual(anyInt(), anyInt());

			assertThat(target.add(2, 3), is(10));
		} catch (Exception e) {

		}
	}
}
